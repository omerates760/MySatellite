package com.omerates.data.model.dto

import com.google.gson.annotations.SerializedName

data class SatelliteDetailItem(
    val id: Int,
    @SerializedName("cost_per_launch")
    val cost_per_launch: Int,
    @SerializedName("first_flight")
    val first_flight: String,
    val height: Int,
    val mass: Int
)