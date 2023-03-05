package com.omerates.data.source.remote

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.omerates.common.extensions.readFile
import com.omerates.data.model.dto.SatelliteDetailItem
import com.omerates.data.model.dto.position.PositionResponse
import com.omerates.data.model.dto.position.SatellitePosition
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RemoteDataSourceImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : RemoteDataSource {
    override fun getSatelliteDetails(): List<SatelliteDetailItem> {
        val jsonString = context.assets.readFile(SATELLITE_DETAIL_FILE_NAME)
        val type = object : TypeToken<ArrayList<SatelliteDetailItem>?>() {}.type
        return Gson().fromJson(jsonString, type)
    }

    override fun getSatellitePositions(): List<SatellitePosition> {
        val jsonString = context.assets.readFile(POSITIONS_FILE_NAME)
        val type = object : TypeToken<PositionResponse>() {}.type
        return Gson().fromJson<PositionResponse?>(jsonString, type).list
    }

    companion object {
        private const val SATELLITE_DETAIL_FILE_NAME = "satellitesDetail.json"
        private const val POSITIONS_FILE_NAME = "positions.json"
    }
}