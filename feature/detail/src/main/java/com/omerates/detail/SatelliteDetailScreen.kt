package com.omerates.detail

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.omerates.detail.composables.SatelliteTile
import com.omerates.feature.detail.R
import com.omerates.ui.components.Error
import com.omerates.ui.components.SatelliteProgressBar

@Composable
fun DetailScreen(viewModel: SatelliteDetailViewModel = hiltViewModel()) {
    val uiState = viewModel.uiState.value
    Column(
        modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            uiState.isLoading -> SatelliteProgressBar()
            uiState.errorMessage.isNotEmpty() || uiState.satelliteUiModel == null -> Error(
                errorMessage = uiState.errorMessage
            )
            else -> {
                SatelliteTile(
                    style = TextStyle(
                        fontWeight = FontWeight.Bold,
                        fontSize = 28.sp
                    ), value = uiState.satelliteUiModel.name
                )
                SatelliteTile(
                    style = TextStyle(
                        fontSize = 15.sp
                    ), value = uiState.satelliteUiModel.firstFlight
                )
                SatelliteTile(
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    value = stringResource(
                        id = R.string.height_mass_key,
                        uiState.satelliteUiModel.height,
                        uiState.satelliteUiModel.mass
                    )
                )
                SatelliteTile(
                    style = TextStyle(
                        fontSize = 20.sp
                    ),
                    value = stringResource(
                        id = R.string.cost_key,
                        uiState.satelliteUiModel.costPerLaunch
                    )
                )
                if (uiState.positions != null) {
                    SatelliteTile(
                        style = TextStyle(
                            fontSize = 18.sp
                        ),
                        value = stringResource(
                            id = R.string.last_position,
                            uiState.positions.posX, uiState.positions.posY
                        )
                    )
                }

            }
        }
    }
}