package com.omerates.satelliteapp.core.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "SatelliteDetail")
data class SatelliteDetailEntity(
    @PrimaryKey
    @ColumnInfo(name = "satelliteId")
    val id: Int,
    val costPerLaunch: Int,
    val firstFlight: String,
    val height: Int,
    val mass: Int
)