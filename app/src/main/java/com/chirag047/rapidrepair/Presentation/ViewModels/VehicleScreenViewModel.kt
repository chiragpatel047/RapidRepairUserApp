package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.VehicleModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class VehicleScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    private val _vehicleList =
        MutableStateFlow<ResponseType<List<VehicleModel>>>(ResponseType.Loading())
    val vehicleList: StateFlow<ResponseType<List<VehicleModel>>>
        get() = _vehicleList

    suspend fun getMyVehicle() {
        viewModelScope.launch {
            dataRepository.getMyVehicles().collect {
                _vehicleList.emit(it)
            }
        }
    }

    suspend fun deleteVehicle(vehicleId : String) = dataRepository.deleteMyVehicle(vehicleId)

}