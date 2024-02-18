package com.chirag047.rapidrepair.Presentation.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chirag047.rapidrepair.Common.ResponseType
import com.chirag047.rapidrepair.Repository.FirebaseAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignUpViewModel @Inject constructor(
    val firebaseAuthRepository: FirebaseAuthRepository
) :
    ViewModel() {

    private val _respose =
        MutableStateFlow<ResponseType<FirebaseUser>>(ResponseType.Loading())

    val response: StateFlow<ResponseType<FirebaseUser>>
        get() = _respose

    suspend fun createUser(userName : String,email: String, password: String) {
        viewModelScope.launch {
            val result = firebaseAuthRepository.createUser(userName,email, password)
            _respose.emit(result)
        }
    }
}