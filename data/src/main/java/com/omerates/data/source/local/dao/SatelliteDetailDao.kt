package com.omerates.data.source.local.dao

import androidx.room.*
import com.omerates.data.model.entity.SatelliteDetailEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SatelliteDetailDao : BaseDao<SatelliteDetailEntity> {

    @Query("SELECT * FROM SatelliteDetail WHERE satelliteId = :id")
    fun getSatelliteDetail(id: Int): Flow<SatelliteDetailEntity?>
}