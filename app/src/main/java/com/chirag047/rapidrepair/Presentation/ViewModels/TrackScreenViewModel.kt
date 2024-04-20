package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Model.OrderModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

@HiltViewModel
class TrackScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {


    private val _requestsData =
        MutableStateFlow<ResponseType<List<OrderModel>>>(ResponseType.Loading())
    val requestsData: StateFlow<ResponseType<List<OrderModel>>>
        get() = _requestsData

    suspend fun getMyRequests(userId: String) {
        dataRepository.getMyRequests(userId).collect {
            _requestsData.emit(it)
        }
    }

}