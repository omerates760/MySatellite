package com.omerates.data.model.mapper

import com.omerates.data.model.dto.SatelliteItem
import com.omerates.domain.model.SatelliteDomainItem
import com.omerates.data.model.entity.SatelliteEntity

fun List<SatelliteItem>.toSatelliteListEntity() = map {
    SatelliteEntity(
        id = it.id,
        name = it.name,
        active = it.active
    )
}

fun List<SatelliteEntity>.toSatelliteListDomainModel() = map {
    SatelliteDomainItem(
        id = it.id,
        name = it.name,
        active = it.active
    )
}

