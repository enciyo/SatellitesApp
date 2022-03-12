package com.enciyo.domain

import com.enciyo.domain.model.Satellite

interface Repository {

    suspend fun getSatellites(): List<Satellite>
}