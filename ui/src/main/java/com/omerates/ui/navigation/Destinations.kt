package com.omerates.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument

object Home : SatelliteDestination {
    override val route: String
        get() = "home_route"
}

object SatelliteDetail : SatelliteDestination {
    override val route: String
        get() = "detail_route"
    private const val satelliteId = "satelliteId"
    val routeWithArgs = "$route/{$satelliteId}"
    val arguments = listOf(
        navArgument(satelliteId) {
            type = NavType.IntType
        }
    )
}