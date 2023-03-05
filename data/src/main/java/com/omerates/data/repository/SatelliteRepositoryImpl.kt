package com.omerates.data.repository

import com.omerates.data.model.mapper.toSatelliteListDomainModel
import com.omerates.data.source.local.dao.SatelliteDao
import com.omerates.domain.model.SatelliteDomainItem
import com.omerates.domain.repository.SatelliteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satelliteDao: SatelliteDao
) : SatelliteRepository {

    override fun searchSatellite(
        name: String
    ): Flow<List<SatelliteDomainItem>> = if (name.isEmpty()) {
        getAllSatellite()
    } else {
        satelliteDao.searchSatellite(name).map { it.toSatelliteListDomainModel() }
    }

    override fun getAllSatellite(): Flow<List<SatelliteDomainItem>> = satelliteDao
        .getAllSatellite().map { it.toSatelliteListDomainModel() }

}