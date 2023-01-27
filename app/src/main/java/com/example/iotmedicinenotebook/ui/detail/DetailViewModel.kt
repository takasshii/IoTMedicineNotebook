package com.example.iotmedicinenotebook.ui.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.iotmedicinenotebook.domain.ArgsGetUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val argsGetUseCase: ArgsGetUseCase
) : ViewModel() {
    private val _detailState = MutableStateFlow(DetailUiState.INITIAL)
    val detailState: StateFlow<DetailUiState> = _detailState.asStateFlow()

    init {
        fetchArgs()
    }

    private fun fetchArgs() {
        // ローディング開始
        _detailState.value = _detailState.value.copy(proceeding = true)

        viewModelScope.launch {
            val args = argsGetUseCase()
            // レスポンスに応じてLiveDataに値を格納
            _detailState.value = args.fold(
                onSuccess = {
                    _detailState.value.copy(
                        medicine = it
                    )
                },
                onFailure = {
                    _detailState.value.copy(error = it.toString())
                },
            )
            // ローディング終了
            _detailState.value = _detailState.value.copy(proceeding = false)
        }
    }
}
