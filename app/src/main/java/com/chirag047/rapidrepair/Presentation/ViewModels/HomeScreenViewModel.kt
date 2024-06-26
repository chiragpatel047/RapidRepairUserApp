package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.CenterModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    private val _centers = MutableStateFlow<ResponseType<List<CenterModel>>>(ResponseType.Loading())

    val centers: StateFlow<ResponseType<List<CenterModel>>>
        get() = _centers

    suspend fun getCenter(cityName: String){
        viewModelScope.launch {
            dataRepository.getCenters(cityName).collect{
                _centers.emit(it)
            }
        }
    }

}