package com.omerates.detail

import com.omerates.domain.model.SatelliteDetailDomainItem

fun SatelliteDetailDomainItem.toSatelliteUiModel() = SatelliteUiModel(
    id = id,
    name = "",
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    mass = mass
)