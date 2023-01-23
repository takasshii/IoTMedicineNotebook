package com.example.iotmedicinenotebook.ui

import androidx.compose.runtime.Composable
import com.example.iotmedicinenotebook.Navigation.MedicineNavHost

@Composable
fun MedicineApp(
    appState: MedicineState = rememberMedicineState(),
) {
    MedicineNavHost(navController = appState.navController)
}