package com.omerates.data.model.mapper

import com.omerates.data.model.dto.SatelliteDetailItem
import com.omerates.domain.model.SatelliteDetailDomainItem
import com.omerates.satelliteapp.core.data.model.entity.SatelliteDetailEntity

fun SatelliteDetailItem.toSatelliteDetailEntity() = SatelliteDetailEntity(
    id = id,
    costPerLaunch = cost_per_launch,
    firstFlight = first_flight,
    height = height,
    mass = mass
)

fun SatelliteDetailEntity.toSatelliteDetailDomainItem() = SatelliteDetailDomainItem(
    id = id,
    costPerLaunch = costPerLaunch,
    firstFlight = firstFlight,
    height = height,
    mass = mass
)

fun SatelliteDetailItem.toSatelliteDetailDomainItem() = SatelliteDetailDomainItem(
    id = id,
    costPerLaunch = cost_per_launch,
    firstFlight = first_flight,
    height = height,
    mass = mass
)
