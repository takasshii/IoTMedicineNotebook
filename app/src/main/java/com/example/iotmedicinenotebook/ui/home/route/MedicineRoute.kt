package com.example.iotmedicinenotebook.ui.home.route

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.iotmedicinenotebook.ui.home.MedicineViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.iotmedicinenotebook.ui.home.MedicineUiState
import com.example.iotmedicinenotebook.ui.home.templates.MedicineScreen

@Composable
fun MedicineRoute(
    navigateToDetail: () -> Unit,
    viewModel: MedicineViewModel = hiltViewModel(),
) {
    val uiState by viewModel.medicineData.collectAsStateWithLifecycle()

    MedicineScreen(
        nextPage = { item -> viewModel.nextPage(item) },
        medicineList = uiState.medicineList,
        modifier = Modifier
    )

    LaunchedEffect(key1 = uiState.events) {
        if (uiState.events.firstOrNull() != null) {
            when (val event = uiState.events.firstOrNull()) {
                is MedicineUiState.Event.Success -> {
                    // イベントを消費
                    viewModel.consumeEvent(event)
                }
                is MedicineUiState.Event.Error -> {
                    // イベントを消費
                    Log.d("RepositoryImpl", uiState.error)
                    viewModel.consumeEvent(event)
                }
                is MedicineUiState.Event.NextPage -> {
                    navigateToDetail()
                    // イベントを消費
                    viewModel.consumeEvent(event)
                }
                else -> {
                    Log.d("TAG", "else pattern")
                }
            }
        }
    }
}