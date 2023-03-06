package com.omerates.data.model.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "Positions")
data class PositionEntity(
    @PrimaryKey
    val id: String = UUID.randomUUID().toString(),
    val satelliteId: Int,
    val posX: Double,
    val posY: Double
)
