package com.enciyo.data

import com.enciyo.domain.model.Satellite
import javax.inject.Inject


class ResourceDataSource @Inject constructor(private val reader: RawReader) {

    suspend fun getSatellites(): List<Satellite> {
        return reader.readList("satellite_list.json", Satellite::class.java).orEmpty()
    }


}