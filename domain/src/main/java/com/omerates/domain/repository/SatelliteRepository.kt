package com.omerates.domain.repository

import com.omerates.domain.model.PositionDomainItem
import com.omerates.domain.model.SatelliteDetailDomainItem
import com.omerates.domain.model.SatelliteDomainItem
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    fun getAllSatellite(): Flow<List<SatelliteDomainItem>>
    fun searchSatellite(name: String): Flow<List<SatelliteDomainItem>>
    fun getSatelliteNameById(id: Int): Flow<String>
    fun getSatelliteDetail(id: Int): Flow<SatelliteDetailDomainItem>
    fun getPositionsById(id: Int): Flow<List<PositionDomainItem>>

}