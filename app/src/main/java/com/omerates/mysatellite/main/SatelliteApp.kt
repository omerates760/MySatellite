package com.omerates.mysatellite.main

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.omerates.mysatellite.main.navigation.SatelliteNavHost

@Composable
fun SatelliteApp() {
    MaterialTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            SatelliteNavHost(navController = navController)
        }
    }
}