package com.omerates.domain.repository

import com.omerates.domain.model.SatelliteDomainItem
import kotlinx.coroutines.flow.Flow

interface SatelliteRepository {
    fun getAllSatellite(): Flow<List<SatelliteDomainItem>>
    fun searchSatellite(name: String): Flow<List<SatelliteDomainItem>>
}