package com.enciyo.data.mapper

import com.enciyo.data.local.SatelliteDetailEntity
import com.enciyo.domain.model.SatelliteDetail
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class SatelliteDetailMapper @Inject constructor() : Mapper<SatelliteDetailEntity, SatelliteDetail> {
    override fun mapTo(input: SatelliteDetailEntity): SatelliteDetail = SatelliteDetail(
        id = input.id,
        costPerLaunch = input.costPerLaunch,
        firstFlight = input.firstFlight,
        height = input.height,
        mass = input.mass
    )
}