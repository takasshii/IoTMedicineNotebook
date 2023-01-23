package com.example.iotmedicinenotebook.ui.detail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.example.iotmedicinenotebook.ui.detail.route.DetailRoute

const val detailNavigationRoute = "result_route"

fun NavController.navigateToDetail(navOptions: NavOptions? = null) {
    this.navigate(detailNavigationRoute, navOptions)
}

fun NavGraphBuilder.detailScreen() {
    composable(
        route = detailNavigationRoute
    ) {
        DetailRoute()
    }
}