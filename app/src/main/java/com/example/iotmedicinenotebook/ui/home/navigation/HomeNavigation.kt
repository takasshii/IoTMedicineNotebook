package com.example.iotmedicinenotebook.ui.home.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.example.iotmedicinenotebook.ui.home.route.MedicineRoute

const val medicineGraphRoutePattern = "medicine_graph"
const val medicineNavigationRoute = "medicine_graph_navigation"

fun NavController.navigateToMedicineGraph(navOptions: NavOptions? = null) {
    this.navigate(medicineGraphRoutePattern, navOptions)
}

fun NavGraphBuilder.medicineGraph(
    navigateToDetail: () -> Unit,
) {
    navigation(
        route = medicineGraphRoutePattern,
        startDestination = medicineNavigationRoute
    ) {
        composable(route = medicineNavigationRoute) {
            MedicineRoute(
                navigateToDetail = navigateToDetail,
            )
        }
    }
}