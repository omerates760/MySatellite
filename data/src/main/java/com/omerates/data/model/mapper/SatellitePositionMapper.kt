package com.omerates.data.model.mapper

import com.omerates.data.model.dto.position.PositionItem
import com.omerates.domain.model.PositionDomainItem
import com.omerates.data.model.entity.PositionEntity

fun List<PositionItem>.toPositionListEntity(id: Int) = map {
    PositionEntity(
        satelliteId = id,
        posX = it.posX,
        posY = it.posY
    )
}

@JvmName("PositionItemsToPositionListDomainItem")
fun List<PositionItem>.toPositionListDomainItem() = map {
    PositionDomainItem(
        posX = it.posX,
        posY = it.posY
    )
}

@JvmName("PositionEntitiesToPositionListDomainItem")
fun List<PositionEntity>.toPositionListDomainItem() = map {
    PositionDomainItem(
        posX = it.posX,
        posY = it.posY
    )
}

