package com.enciyo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class SatelliteDetailEntity(
    @PrimaryKey(autoGenerate = false)
    var id: Int,
    val costPerLaunch: Double,
    val firstFlight: String,
    val height: Int,
    val mass: Int,
)