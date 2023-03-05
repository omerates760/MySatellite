package com.omerates.home

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController

@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = homeViewModel.uiState.value

}
