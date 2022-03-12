package com.enciyo.domain.model

import com.squareup.moshi.Json

data class SatelliteDetail(
    val id: Int,
    @Json(name = "cost_per_launch") val costPerLaunch: Int,
    @Json(name = "first_flight") val firstFlight: String,
    val height: Int,
    val mass: Int,
)