package com.chirag047.rapidrepair.Presentation.ViewModels

import android.content.Context
import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Repository.LocationRespository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddDetailsViewModel @Inject constructor(val locationRespository: LocationRespository) :
    ViewModel() {

    fun getUserCurrentLocation(context: Context) =
        locationRespository.getUserCurrentLocation(context)
}