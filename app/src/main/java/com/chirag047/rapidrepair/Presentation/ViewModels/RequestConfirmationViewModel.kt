package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Model.OrderModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RequestConfirmationViewModel @Inject constructor(val dataRepository: DataRepository) :
    ViewModel() {

    suspend fun submitOrder(orderModel: OrderModel) = dataRepository.submitOrder(orderModel)
}