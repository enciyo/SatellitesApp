package com.enciyo.satellitesapp.ext

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData


fun <T> MutableLiveData<T>.toLiveData() = this as LiveData<T>