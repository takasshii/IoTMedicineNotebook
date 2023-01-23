package com.example.iotmedicinenotebook.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iotmedicinenotebook.core.domain.Medicine
import com.example.iotmedicinenotebook.data.room.medicine.MedicineEntity
import com.example.iotmedicinenotebook.domain.ArgsWriteUseCase
import com.example.iotmedicinenotebook.domain.FireStoreUseCase
import com.example.iotmedicinenotebook.domain.MedicineDBUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class MedicineViewModel @Inject constructor(
    private val fireStoreUseCase: FireStoreUseCase,
    private val argsWriteUseCase: ArgsWriteUseCase,
    private val medicineDBUseCase : MedicineDBUseCase
) : ViewModel() {

    private val _medicineData = MutableStateFlow(MedicineUiState.INITIAL)
    val medicineData = _medicineData.asStateFlow()

    private val exceptionHandler = CoroutineExceptionHandler { _, throwable ->
        dispatch(
            MedicineEvent.UnknownExpectedError(
                errorMessage = throwable.toString(),
            ),
        )
    }

    init {
        getAllMedicine(limit = 10)
    }

    private fun getAllMedicine(limit: Long) {
        viewModelScope.launch {
            // ローディング開始
            _medicineData.value = _medicineData.value.copy(proceeding = true)
            val result = medicineDBUseCase(limit = limit)

            result.fold(
                onSuccess = {
                    Log.d("RepositoryImpl", "Success in ViewModel contents : $it")
                    dispatch(
                        MedicineEvent.MedicineList(medicineList = it)
                    )
                },
                onFailure = {
                    Log.d("RepositoryImpl", "Failure because $it")
                    dispatch(MedicineEvent.UnknownExpectedError(errorMessage = it.toString()))
                },
            )
            _medicineData.value = _medicineData.value.copy(proceeding = false)
        }
    }

    private val scope: CoroutineScope
        get() = viewModelScope + exceptionHandler

    private fun dispatch(event: MedicineEvent) {
        scope.launch {
            updateState(event)
            Log.v("Debug", "For Debug ${medicineData.value}")
        }
    }

    private fun updateState(event: MedicineEvent) {
        _medicineData.value = when (event) {
            is MedicineEvent.MedicineList -> {
                // ViewModelイベント発行
                val newEvents = _medicineData.value.events.plus(MedicineUiState.Event.Success)
                _medicineData.value.copy(
                    events = newEvents,
                    medicineList = event.medicineList
                )
            }
            is MedicineEvent.UnknownExpectedError -> {
                // ViewModelイベント発行
                val newEvents =
                    _medicineData.value.events.plus(MedicineUiState.Event.Error(event.errorMessage))
                // 値をセット
                _medicineData.value.copy(events = newEvents)
            }
            else -> {
                _medicineData.value.copy(
                    error = "対応していないファイル形式です"
                )
            }
        }
    }

    // イベントを消費する関数
    fun consumeEvent(event: MedicineUiState.Event) {
        val newEvents = _medicineData.value.events.filterNot { it == event }
        _medicineData.value = _medicineData.value.copy(events = newEvents)
    }

    // 次のページに進むEventを発行する関数
    fun nextPage(result: MedicineEntity) {
        val newEvents =
            _medicineData.value.events.plus(MedicineUiState.Event.NextPage(result))
        _medicineData.value = _medicineData.value.copy(events = newEvents)
    }

    fun pushArgs(args: MedicineEntity) {
        viewModelScope.launch {
            // ローディング開始
            _medicineData.value = _medicineData.value.copy(proceeding = true)

            val result = argsWriteUseCase(args)
            result.fold(
                onSuccess = {},
                onFailure = {
                    // ViewModelイベント発行
                    val newEvents =
                        _medicineData.value.events.plus(MedicineUiState.Event.Error(it.toString()))
                    // 値をセット
                    _medicineData.value.copy(events = newEvents)
                },
            )
            // ローディング終了
            _medicineData.value = _medicineData.value.copy(proceeding = false)
        }
    }
}