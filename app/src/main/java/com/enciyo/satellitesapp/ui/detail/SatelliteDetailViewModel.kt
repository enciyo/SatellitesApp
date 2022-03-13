package com.enciyo.satellitesapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.enciyo.domain.SatelliteDetailGetUseCase
import com.enciyo.domain.SatelliteDetailPositionGetUseCase
import com.enciyo.domain.model.Position
import com.enciyo.domain.model.SatelliteDetail
import com.enciyo.satellitesapp.ui.base.BaseViewModel
import com.enciyo.satellitesapp.ui.ext.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onCompletion
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.isActive
import javax.inject.Inject

@HiltViewModel
class SatelliteDetailViewModel @Inject constructor(
    private val satelliteDetailGetUseCase: SatelliteDetailGetUseCase,
    private val satelliteDetailPositionGetUseCase: SatelliteDetailPositionGetUseCase,
) : BaseViewModel() {

    private val _detail = MutableLiveData<SatelliteDetail>()
    val detail = _detail.toLiveData()

    private val _lastPosition = MutableLiveData<Position>()
    val lastPosition = _lastPosition.toLiveData()

    fun getById(id: Int) {
        satelliteDetailGetUseCase.invoke(SatelliteDetailGetUseCase.Request(id))
            .handle(consumer = _detail::setValue)
        getLastPosition(id)
    }

    private fun getLastPosition(id: Int) {
        satelliteDetailPositionGetUseCase.invoke(SatelliteDetailPositionGetUseCase.Request(id))
            .handle(consumer = ::startRetryOperation)
    }

    private fun startRetryOperation(positions: List<Position>) {
        positions.asFlow()
            .onEach {
                _lastPosition.value = it
                delay(3000)
            }
            .onCompletion {
                if (viewModelScope.isActive) startRetryOperation(positions)
            }
            .launchIn(viewModelScope)
    }


}