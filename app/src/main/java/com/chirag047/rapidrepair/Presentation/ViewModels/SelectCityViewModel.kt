package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class SelectCityViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    suspend fun updateUserCity(city: String) = dataRepository.updateUserCity(city)

}