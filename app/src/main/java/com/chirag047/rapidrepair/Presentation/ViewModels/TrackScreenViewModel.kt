package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import com.chirag047.rapidrepair.Repository.DataRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class TrackScreenViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    suspend fun getMyOrdersRequest(userId: String, requestType: String) =
        dataRepository.getMyOrdersRequest(userId, requestType)

}