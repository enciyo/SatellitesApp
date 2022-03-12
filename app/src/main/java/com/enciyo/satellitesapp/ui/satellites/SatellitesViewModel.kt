package com.enciyo.satellitesapp.ui.satellites

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.enciyo.domain.SatellitesGetUseCase
import com.enciyo.domain.model.Satellite
import com.enciyo.satellitesapp.ui.base.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val satellitesGetUseCase: SatellitesGetUseCase,
) : BaseViewModel() {

    private val _satellites = MutableLiveData<List<Satellite>>()
    val satellites = _satellites as LiveData<List<Satellite>>

    init {
        getSatellites()
    }

    private fun getSatellites() {
        satellitesGetUseCase.invoke()
            .handle(consumer = _satellites::setValue)
    }

}