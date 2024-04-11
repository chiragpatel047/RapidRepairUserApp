package com.chirag047.rapidrepair.Presentation.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Repository.LocationRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ChooseLocationViewModel @Inject constructor(val locationRepository: LocationRespository) :
    ViewModel() {
    suspend fun getUserCurrentLocation(context: Context) =
        locationRepository.getUserCurrentLocation(context)

}