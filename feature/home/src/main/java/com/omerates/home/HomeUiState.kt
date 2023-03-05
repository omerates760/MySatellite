package com.omerates.home

data class HomeUiState(
    val isLoading: Boolean = false,
    val satellites: List<SatelliteUiModel> = emptyList(),
    val searchQuery: String = "",
    val errorMessage: String = ""
)

data class SatelliteUiModel(
    val id: Int, val name: String, val type: SatelliteCurrentType
)

enum class SatelliteCurrentType(val readableName: String) {
    ACTIVE("Active"), PASSIVE("Passive")
}