package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Repository.DataRepository
import com.chirag047.rapidrepair.Repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    val firebaseAuthRepository: FirebaseAuthRepository,
    val dataRepository: DataRepository
) :
    ViewModel() {
    fun loginUser(email: String, password: String) =
        firebaseAuthRepository.loginUser(email, password)

    suspend fun getUserDetails() = dataRepository.getUserDetails()

}