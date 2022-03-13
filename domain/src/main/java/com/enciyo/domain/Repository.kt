package com.enciyo.domain

import com.enciyo.domain.model.Position
import com.enciyo.domain.model.Positions
import com.enciyo.domain.model.Satellite
import com.enciyo.domain.model.SatelliteDetail

interface Repository {
    suspend fun getSatellites(): List<Satellite>
    suspend fun getSatelliteById(id: Int): SatelliteDetail
    suspend fun getSatelliteByIdPositions(id: Int): List<Position>
}