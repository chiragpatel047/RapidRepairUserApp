package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.CenterModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {


    suspend fun getCenter(cityName : String) = dataRepository.getCenters(cityName)

}