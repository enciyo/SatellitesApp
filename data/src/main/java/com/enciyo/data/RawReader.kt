package com.enciyo.data

import android.content.Context
import com.enciyo.domain.model.SerializationException
import com.enciyo.shared.IoDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class RawReader @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher val ioDispatcher: CoroutineDispatcher,
    private val moshi: Moshi,
) {

    suspend fun <T> read(fileName: String, `class`: Class<T>): T? = withContext(ioDispatcher) {
        val jsonString = readText(fileName)
        return@withContext catchExceptionAndWrap {
            moshi.adapter(`class`).fromJson(jsonString)
        }
    }

    suspend fun <T> readList(fileName: String, `class`: Class<T>): List<T>? =
        withContext(ioDispatcher) {
            val types = Types.newParameterizedType(List::class.java, `class`)
            val jsonString = readText(fileName)
            return@withContext catchExceptionAndWrap {
                moshi.adapter<List<T>>(types).fromJson(jsonString)
            }
        }

    private fun readText(fileName: String): String {
        return context.resources.assets.open(fileName).bufferedReader().use { it.readText() }
    }

    private fun <T> catchExceptionAndWrap(block: () -> T): T {
        try {
            return block.invoke()
        } catch (e: Exception) {
            throw SerializationException(e.message)
        }
    }

}