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
class ProfileViewModel @Inject constructor(val dataRepository: DataRepository) : ViewModel() {

    suspend fun updateUserProfilePictureAndPhone(
        userImage: String,
        userName: String,
        phoneNo: String
    ) =
        dataRepository.updateUserProfilePictureAndPhone(userImage, userName, phoneNo)

    suspend fun getUserDetails() = dataRepository.getUserDetails()
}