package com.enciyo.domain

import com.enciyo.domain.model.Position
import com.enciyo.shared.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class SatelliteDetailPositionGetUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: Repository,
) : BaseUseCase<SatelliteDetailPositionGetUseCase.Request, List<Position>>(ioDispatcher) {
    override suspend fun execute(parameters: Request): List<Position> =
        repository.getSatelliteByIdPositions(parameters.id)

    data class Request(val id: Int)
}