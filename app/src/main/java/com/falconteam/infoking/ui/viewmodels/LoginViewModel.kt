package com.falconteam.infoking.ui.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.models.User
import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    val data = mutableStateOf<LoginResponse>(
        LoginResponse(
            "",
            "",
            User(
                "",
                "",
                "",
            )
        )
    )
    val errors: MutableState<String> = mutableStateOf("")

    val repository_Login = RetrofitApplication()._loginRepository

    fun Login(LoginRequest: LoginRequest) {
        viewModelScope.launch {
            val value = repository_Login.Login(LoginRequest)
            when (value) {
                is ApiResponse.Success -> {
                    data.value = value.data
                }
                is ApiResponse.Error -> {
                    errors.value = value.exception.message.toString()
                }
                is ApiResponse.ErrorWithMessage -> {
                    errors.value = value.message
                }
                else -> {
                    errors.value = "Error desconocido"
                }
            }
        }
    }
}