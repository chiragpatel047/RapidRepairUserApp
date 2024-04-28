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


    private val _updateProfilePic = MutableStateFlow<ResponseType<String>>(ResponseType.Loading())

    val updateProfilePic: StateFlow<ResponseType<String>>
        get() = _updateProfilePic

    suspend fun updateUserProfilePictureAndPhone(userImage: String, phoneNo: String) {
        viewModelScope.launch {
            dataRepository.updateUserProfilePictureAndPhone(userImage, phoneNo).collect {
                _updateProfilePic.emit(it)
            }
        }
    }
}