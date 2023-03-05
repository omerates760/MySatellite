package com.omerates.home

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.omerates.feature.home.R
import com.omerates.home.composables.SatelliteList
import com.omerates.ui.components.Error
import com.omerates.ui.components.NoSearchResult
import com.omerates.ui.components.SatelliteProgressBar
import com.omerates.ui.components.SearchBar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    val uiState = homeViewModel.uiState.value

    Scaffold(
        topBar = {
            SearchBar(
                query = uiState.searchQuery,
                searchPlaceholder = stringResource(id = R.string.search_place_holder),
                onValueChanged = {
                    homeViewModel.onEvent(HomeEvent.QueryChangeEvent(it))
                },
                onSearch = {
                    homeViewModel.onEvent(HomeEvent.ExecuteSearchEvent)
                },
                onDeletePressed = {
                    homeViewModel.onEvent(HomeEvent.QueryChangeEvent(""))
                }
            )
        }
    ) {
        when {
            uiState.isLoading -> SatelliteProgressBar()
            uiState.errorMessage.isNotEmpty() -> Error(
                errorMessage = uiState.errorMessage
            )
            uiState.satellites.isEmpty() -> NoSearchResult()
        }
        SatelliteList(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
            satellites = uiState.satellites,
            onSatelliteClicked = { satelliteItem ->
            })
    }
}
