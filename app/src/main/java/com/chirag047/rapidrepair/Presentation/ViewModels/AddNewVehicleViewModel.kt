package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Model.VehicleModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class AddNewVehicleViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    suspend fun addNewVehicle(vehicleModel: VehicleModel) =
        dataRepository.addNewVehicle(vehicleModel)

}