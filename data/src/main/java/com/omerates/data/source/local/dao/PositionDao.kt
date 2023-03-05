package com.omerates.data.source.local.dao

import androidx.room.*
import com.omerates.satelliteapp.core.data.model.entity.PositionEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PositionDao : BaseDao<PositionEntity> {
    @Query("SELECT * FROM Positions WHERE satelliteId = :id")
    fun getPositionsById(id: Int): Flow<List<PositionEntity>?>
}