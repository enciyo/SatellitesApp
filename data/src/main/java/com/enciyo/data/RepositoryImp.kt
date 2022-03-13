package com.enciyo.data

import com.enciyo.data.local.LocalDataSource
import com.enciyo.domain.Repository
import com.enciyo.domain.model.Position
import com.enciyo.domain.model.Positions
import com.enciyo.domain.model.Satellite
import com.enciyo.domain.model.SatelliteDetail
import java.lang.IllegalStateException
import javax.inject.Inject
import javax.inject.Singleton


class RepositoryImp constructor(
    private val resourceDataSource: ResourceDataSource,
    private val localDataSource: LocalDataSource,
) : Repository {

    override suspend fun getSatellites(): List<Satellite> =
        resourceDataSource.getSatellites()

    override suspend fun getSatelliteById(id: Int): SatelliteDetail {
        localDataSource.findById(id)?.let { return it }
        return resourceDataSource.getSatelliteById(id)?.also {
            localDataSource.save(it)
        } ?: throw IllegalStateException("Not Found")
    }

    override suspend fun getSatelliteByIdPositions(id: Int): List<Position> =
        resourceDataSource.getSatelliteByIdPositions(id)
}


