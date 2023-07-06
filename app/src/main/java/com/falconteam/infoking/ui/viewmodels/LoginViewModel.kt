package com.falconteam.infoking.ui.viewmodels

import android.content.Context
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.models.LoginDataResponse
import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import com.falconteam.infoking.ui.components.PreferencesKeys
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.setFullData
import com.falconteam.infoking.ui.components.setFullDataUser
import kotlinx.coroutines.launch

class LoginViewModel() : ViewModel() {
    val data = mutableStateMapOf<Int, LoginResponse>()
    val predata = mutableStateMapOf<Int, LoginDataResponse>()
    val finished = mutableStateOf(false)
    val errors: MutableState<String> = mutableStateOf("")

    val repository_Login = RetrofitApplication()._loginRepository

    fun getVersion(): String {
        var version = ""
        viewModelScope.launch {
            version = repository_Login.getVersion()
        }
        return version
    }

    fun Login(context: Context, LoginRequest: LoginRequest): Any? {
        viewModelScope.launch {
            val value = repository_Login.Login(LoginRequest)
            when (value) {
                is ApiResponse.Success -> {
                    data[0] = value.data
                    setFullData(
                        context = context,
                        data = value.data,
                    )
                    getData(context, keyString = PreferencesKeys.TOKEN, type = 1)
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
            return data[0]
        } else {
            return errors.value
        }
    }

    fun getUserData(context: Context, id: String) {
        viewModelScope.launch {
            if (!finished.value) {
                val value = repository_Login.getUserData(id)
                when (value) {
                    is ApiResponse.Success -> {
                        predata[0] = value.data
                        setFullDataUser(
                            context = context,
                            data = value.data,
                        )
                        finished.value = true
                    }

                    is ApiResponse.Error -> {
                        errors.value = value.exception.message.toString()
                        finished.value = true
                    }

                    is ApiResponse.ErrorWithMessage -> {
                        errors.value = value.message
                        finished.value = true
                    }

                    else -> {
                        errors.value = "Error desconocido"
                        finished.value = true
                    }
                }
            }
        }
    }

}