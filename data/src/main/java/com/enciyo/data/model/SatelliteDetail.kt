package com.enciyo.data.model

import com.squareup.moshi.Json
import java.math.BigInteger

data class SatelliteDetail(
    val id: Int,
    @Json(name = "cost_per_launch") val costPerLaunch: BigInteger,
    @Json(name = "first_flight") val firstFlight: String,
    val height: Int,
    val mass: Int,
)