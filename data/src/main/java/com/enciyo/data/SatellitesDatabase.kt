package com.enciyo.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.enciyo.data.local.SatelliteDetailDao
import com.enciyo.data.local.SatelliteDetailEntity


@Database(
    entities = [
        SatelliteDetailEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class SatellitesDatabase : RoomDatabase() {
    abstract fun satelliteDetailDao(): SatelliteDetailDao
}