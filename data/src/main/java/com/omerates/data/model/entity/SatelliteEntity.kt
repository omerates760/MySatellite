package com.omerates.satelliteapp.core.data.model.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Satellites")
data class SatelliteEntity(
    @PrimaryKey
    @ColumnInfo(name = "satelliteId")
    val id: Int,
    @ColumnInfo(name = "name")
    val name: String = "",
    @ColumnInfo(name = "active")
    val active: Boolean = false
)