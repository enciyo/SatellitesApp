package com.enciyo.data

import android.content.Context
import androidx.annotation.RawRes
import com.enciyo.domain.model.SerializationException
import com.enciyo.shared.IoDispatcher
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import java.lang.Exception
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext


@Singleton
class RawReader @Inject constructor(
    @ApplicationContext private val context: Context,
    @IoDispatcher val ioDispatcher: CoroutineContext,
    private val moshi: Moshi,
) {

    suspend fun <T> read(@RawRes id: Int, classs: Class<T>): T? = withContext(ioDispatcher) {
        val reader = context.resources.openRawResource(id).bufferedReader().use { it.readText() }
        return@withContext catchExceptionAndWrap {
            moshi.adapter(classs).fromJson(reader)
        }
    }

    suspend fun <T> readList(@RawRes id: Int, classs: Class<T>): List<T>? = withContext(ioDispatcher) {
        val types = Types.newParameterizedType(List::class.java, classs)
        val reader = context.resources.openRawResource(id).bufferedReader().use { it.readText() }
        return@withContext moshi.adapter<List<T>>(types).fromJson(reader)
    }

    private fun <T> catchExceptionAndWrap(block: () -> T): T {
        try {
            return block.invoke()
        } catch (e: Exception) {
            throw SerializationException(e.message)
        }
    }

}