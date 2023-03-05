package com.omerates.data.source.remote

import com.omerates.data.model.dto.SatelliteDetailItem
import com.omerates.data.model.dto.position.SatellitePosition

interface RemoteDataSource {
    fun getSatelliteDetails(): List<SatelliteDetailItem>
    fun getSatellitePositions(): List<SatellitePosition>
}