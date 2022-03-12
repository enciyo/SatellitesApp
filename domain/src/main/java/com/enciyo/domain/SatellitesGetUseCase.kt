package com.enciyo.domain

import com.enciyo.domain.model.Satellite
import com.enciyo.shared.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject


class SatellitesGetUseCase @Inject constructor(
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher,
    private val repository: Repository,
) : BaseUseCase<Any, List<Satellite>>(ioDispatcher) {
    override suspend fun execute(parameters: Any?): List<Satellite> {
        return repository.getSatellites()
    }

}