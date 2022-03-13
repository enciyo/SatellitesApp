package com.enciyo.data.mapper

import com.enciyo.data.local.SatelliteDetailEntity
import com.enciyo.domain.model.SatelliteDetail
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SatelliteDetailEntityMapper @Inject constructor() :
    Mapper<SatelliteDetail, SatelliteDetailEntity> {
    override fun mapTo(input: SatelliteDetail): SatelliteDetailEntity =
        SatelliteDetailEntity(
            id = input.id,
            costPerLaunch = input.costPerLaunch,
            firstFlight = input.firstFlight,
            height = input.height,
            mass = input.mass
        )
}