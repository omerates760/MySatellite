package com.omerates.home

import com.omerates.domain.model.SatelliteDomainItem

fun List<SatelliteDomainItem>.toSatelliteListUiModel() = map {
    SatelliteUiModel(
        id = it.id,
        name = it.name,
        type = if (it.active) SatelliteCurrentType.ACTIVE else SatelliteCurrentType.PASSIVE
    )
}