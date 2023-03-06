package com.omerates.data.repository

import com.omerates.data.model.mapper.*
import com.omerates.data.source.local.dao.PositionDao
import com.omerates.data.source.local.dao.SatelliteDao
import com.omerates.data.source.local.dao.SatelliteDetailDao
import com.omerates.data.source.remote.RemoteDataSource
import com.omerates.domain.model.PositionDomainItem
import com.omerates.domain.model.SatelliteDetailDomainItem
import com.omerates.domain.model.SatelliteDomainItem
import com.omerates.domain.repository.SatelliteRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

class SatelliteRepositoryImpl @Inject constructor(
    private val satelliteDao: SatelliteDao,
    private val satelliteDetailDao: SatelliteDetailDao,
    private val positionDao: PositionDao,
    private val remoteDataSource: RemoteDataSource
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

    override fun getSatelliteNameById(id: Int): Flow<String> = satelliteDao
        .getSatelliteNameById(id)

    override fun getSatelliteDetail(id: Int): Flow<SatelliteDetailDomainItem> {
        return channelFlow {
            val response = satelliteDetailDao.getSatelliteDetail(id).firstOrNull()
            if (response == null) {
                remoteDataSource.getSatelliteDetails().find {
                    it.id == id
                }?.let {
                    launch(Dispatchers.IO) {
                        satelliteDetailDao.insert(it.toSatelliteDetailEntity())
                    }
                    send(it.toSatelliteDetailDomainItem())
                }
            } else {
                send(response.toSatelliteDetailDomainItem())
            }
        }
    }

    override fun getPositionsById(id: Int): Flow<List<PositionDomainItem>> {
        return channelFlow {
            val response = positionDao.getPositionsById(id).firstOrNull()
            if (response.isNullOrEmpty()) {
                remoteDataSource.getSatellitePositions().find {
                    it.id.toInt() == id
                }?.positions?.let {
                    launch(Dispatchers.IO) {
                        positionDao.insertAll(it.toPositionListEntity(id))
                    }
                    send(it.toPositionListDomainItem())
                }
            } else {
                send(response.toPositionListDomainItem())
            }
        }
    }
}