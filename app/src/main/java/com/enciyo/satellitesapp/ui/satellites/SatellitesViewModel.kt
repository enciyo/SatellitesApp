package com.enciyo.satellitesapp.ui.satellites

import androidx.lifecycle.MutableLiveData
import com.enciyo.domain.SatellitesGetUseCase
import com.enciyo.domain.model.Satellite
import com.enciyo.satellitesapp.ui.base.BaseViewModel
import com.enciyo.satellitesapp.ext.toLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SatellitesViewModel @Inject constructor(
    private val satellitesGetUseCase: SatellitesGetUseCase,
) : BaseViewModel() {

    private val _satellites = MutableLiveData<List<Satellite>>()
    val satellites = _satellites.toLiveData()

    fun getSatellites() {
        satellitesGetUseCase.invoke("").handle(consumer = _satellites::setValue)
    }

}
