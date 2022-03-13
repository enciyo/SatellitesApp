package com.enciyo.satellitesapp.ui.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import arrow.core.Either
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import java.lang.Exception


abstract class BaseViewModel : ViewModel() {

    private val _loading = MutableLiveData<Boolean>()
    val loading = _loading as LiveData<Boolean>

    fun <I> Flow<Either<Exception, I>>.handle(
        isShowLoading: Boolean = true,
        isShowError: Boolean = false,
        consumer: (I) -> Unit,
    ) {
        this
            .onEach {
                when (it) {
                    is Either.Left -> {
                        //Show UI or record to Firebase
                        Log.i("MyLogger", it.value.message.orEmpty())
                    }
                    is Either.Right -> consumer.invoke(it.value)
                }

            }
            .onStart {
                if (isShowLoading) _loading.value = true
            }
            .onCompletion {
                if (isShowLoading) _loading.value = false
            }
            .launchIn(viewModelScope)
    }


}