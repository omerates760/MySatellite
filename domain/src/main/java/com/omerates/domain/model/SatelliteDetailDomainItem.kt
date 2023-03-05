package com.omerates.domain.model

data class SatelliteDetailDomainItem(
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)