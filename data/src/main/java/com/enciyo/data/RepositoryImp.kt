package com.enciyo.data

import com.enciyo.domain.Repository
import com.enciyo.domain.model.Satellite
import javax.inject.Inject
import javax.inject.Singleton


class RepositoryImp constructor(
    private val resourceDataSource: ResourceDataSource,
) : Repository {

    override suspend fun getSatellites(): List<Satellite> {
        return resourceDataSource.getSatellites()
    }


}