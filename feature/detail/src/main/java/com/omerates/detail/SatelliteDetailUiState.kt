package com.omerates.detail

data class SatelliteDetailUiState(
    val isLoading: Boolean = false,
    val satelliteUiModel: SatelliteUiModel? = null,
    val positions: PositionUiModel? = null,
    val errorMessage: String = ""
)

data class SatelliteUiModel(
    val id: Int,
    val name: String,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)

data class PositionUiModel(
    val posX: Double,
    val posY: Double
)