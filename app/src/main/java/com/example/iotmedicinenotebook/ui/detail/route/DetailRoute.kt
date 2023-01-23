package com.example.iotmedicinenotebook.ui.detail.route

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.iotmedicinenotebook.ui.detail.DetailViewModel
import com.example.iotmedicinenotebook.ui.detail.templates.DetailScreen

@Composable
fun DetailRoute(
    viewModel: DetailViewModel = hiltViewModel(),
) {
    val uiState by viewModel.detailState.collectAsState()

    DetailScreen(
        medicine = uiState.medicine,
        proceeding = uiState.proceeding,
        modifier = Modifier
    )
}
