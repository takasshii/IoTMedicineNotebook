package com.example.iotmedicinenotebook.Navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.example.iotmedicinenotebook.ui.detail.navigation.detailScreen
import com.example.iotmedicinenotebook.ui.detail.navigation.navigateToDetail
import com.example.iotmedicinenotebook.ui.home.navigation.medicineGraph
import com.example.iotmedicinenotebook.ui.home.navigation.medicineGraphRoutePattern

@Composable
fun MedicineNavHost(
    navController: NavHostController,
    startDestination: String = medicineGraphRoutePattern,
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
    ) {
        medicineGraph(
            navigateToDetail = {
                navController.navigateToDetail()
            }
        )
        detailScreen()
    }
}