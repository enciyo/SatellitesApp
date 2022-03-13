package com.enciyo.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query


@Dao
interface SatelliteDetailDao {

    @Query("Select * from satellitedetailentity")
    suspend fun getAll(): List<SatelliteDetailEntity>

    @Query("SELECT * FROM satellitedetailentity where id == :id")
    suspend fun finById(id: Int): SatelliteDetailEntity?

    @Insert
    suspend fun insert(satelliteDetailEntity: SatelliteDetailEntity)

}