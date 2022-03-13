package com.enciyo.data

import com.enciyo.domain.model.AssetFiles
import com.enciyo.domain.model.PositionsResult
import com.enciyo.domain.model.Satellite
import com.enciyo.domain.model.SatelliteDetail
import com.enciyo.shared.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject


class ResourceDataSource @Inject constructor(
    private val reader: RawReader,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
) {

    suspend fun getSatellites(): List<Satellite> =
        reader.readList(AssetFiles.LIST.fileName, Satellite::class.java).orEmpty()

    suspend fun getSatelliteById(satelliteId: Int): SatelliteDetail? = withContext(ioDispatcher) {
        return@withContext reader.readList(AssetFiles.DETAIL.fileName, SatelliteDetail::class.java)
            .orEmpty()
            .find { it.id == satelliteId }
    }

    suspend fun getSatelliteByIdPositions(satelliteId: Int) = withContext(ioDispatcher) {
        return@withContext reader.read(AssetFiles.POSITION.fileName, PositionsResult::class.java)
            ?.list
            ?.find { it.id == satelliteId.toString() }
            ?.positions
            .orEmpty()
    }

}