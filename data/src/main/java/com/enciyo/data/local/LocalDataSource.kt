package com.enciyo.data.local

import com.enciyo.data.SatellitesDatabase
import com.enciyo.data.mapper.SatelliteDetailEntityMapper
import com.enciyo.data.mapper.SatelliteDetailMapper
import com.enciyo.domain.model.SatelliteDetail
import com.enciyo.shared.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class LocalDataSource @Inject constructor(
    private val satellitesDatabase: SatellitesDatabase,
    private val satelliteDetailMapper: SatelliteDetailMapper,
    private val satelliteDetailEntityMapper: SatelliteDetailEntityMapper,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun findById(id: Int) = withContext(ioDispatcher) {
        satellitesDatabase.satelliteDetailDao().finById(id)
            ?.let {
                return@withContext satelliteDetailMapper.mapTo(it)
            }
    }

    suspend fun save(data: SatelliteDetail) = withContext(ioDispatcher) {
        val entity = satelliteDetailEntityMapper.mapTo(data)
        satellitesDatabase.satelliteDetailDao().insert(entity)
    }

}