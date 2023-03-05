package com.omerates.data.source.local.dao

import androidx.room.*
import com.omerates.satelliteapp.core.data.model.entity.SatelliteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface SatelliteDao : BaseDao<SatelliteEntity> {

    @Query("SELECT * FROM Satellites")
    fun getAllSatellite(): Flow<List<SatelliteEntity>>

    @Query("SELECT name FROM Satellites WHERE satelliteId = :id")
    fun getSatelliteNameById(id: Int): Flow<String>

    @Query("SELECT * FROM Satellites WHERE name LIKE '%' || :name || '%'")
    fun searchSatellite(name: String): Flow<List<SatelliteEntity>>

    @Query("SELECT * FROM Satellites WHERE satelliteId = :id")
    fun getSatelliteDetail(id: String): Flow<SatelliteEntity>
}