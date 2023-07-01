package com.falconteam.infoking.ui.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.setData
import com.falconteam.infoking.ui.components.setFullData
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    val data = mutableStateMapOf<Int, LoginResponse>()
    val errors: MutableState<String> = mutableStateOf("")

    val repository_Login = RetrofitApplication()._loginRepository

    fun Login(context: Context, LoginRequest: LoginRequest): Any? {
        viewModelScope.launch {
            val value = repository_Login.Login(LoginRequest)
            when (value) {
                is ApiResponse.Success -> {
                    Log.d("Pruebas", "Login: ${value.data}")
                    data[0] = value.data
                    setFullData(
                        context = context,
                        data = value.data,
                    )
                    val token = getData(context, keyString = PreferencesKeys.TOKEN, type = 1)
                    Log.d("Pruebas", "Login: $token")
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
        if (errors.value == "" && data.isNotEmpty()) {
            Log.d("Pruebas", "Login: ${data[0]}")
            return data[0]
        } else {
            return errors.value
        }
    }
}