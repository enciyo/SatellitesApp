package com.enciyo.domain

import arrow.core.Either
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.withContext
import java.lang.Exception


abstract class BaseUseCase<in I, out O>(
    private val ioDispatcher: CoroutineDispatcher,
) {

    operator fun invoke(input: I? = null): Flow<Either<Exception, O>> {
        return flow<Either<Exception, O>> {
            delay(500)
            emit(Either.Right(execute(input)))
        }
            .flowOn(ioDispatcher)
            .catch { e ->
                emit(Either.Left(Exception(e.message.orEmpty())))
            }
    }

    abstract suspend fun execute(parameters: I? = null): O

}