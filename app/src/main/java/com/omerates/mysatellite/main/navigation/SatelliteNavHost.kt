package com.omerates.mysatellite.main.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.omerates.home.HomeScreen
import com.omerates.ui.navigation.Home

@Composable
fun SatelliteNavHost(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Home.route) {
        composable(route = Home.route) {
            HomeScreen(navController = navController)
        }
    }
}