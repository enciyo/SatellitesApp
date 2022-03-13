package com.enciyo.domain

import com.enciyo.domain.model.SatelliteDetail
import com.enciyo.shared.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class SatelliteDetailGetUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: Repository,
) : BaseUseCase<SatelliteDetailGetUseCase.Request, SatelliteDetail>(ioDispatcher) {
    override suspend fun execute(parameters: Request): SatelliteDetail =
        repository.getSatelliteById(parameters.id)

    data class Request(val id: Int)

}