package com.example.iotmedicinenotebook.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

@Composable
fun rememberMedicineState(
    navController: NavHostController = rememberNavController()
) : MedicineState {
    return remember(navController) {
        MedicineState(navController)
    }
}

@Stable
class MedicineState(
    val navController: NavHostController
) {
    // ここで拡張関数を定義
}