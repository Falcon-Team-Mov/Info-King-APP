package com.falconteam.infoking.ui.viewmodels

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
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
import com.falconteam.infoking.ui.components.PreferencesKeys.TIME_PLAYING
import com.falconteam.infoking.ui.components.getData
import com.falconteam.infoking.ui.components.setData
import com.falconteam.infoking.ui.components.setFullData
import com.falconteam.infoking.ui.components.setFullDataUser
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class LoginViewModel : ViewModel() {
    val data = mutableStateMapOf<Int, LoginResponse>()
    val predata = mutableStateMapOf<Int, LoginDataResponse>()
    val finished = mutableStateOf(false)
    val startcount = mutableStateOf(false)
    val errors: MutableState<String> = mutableStateOf("")
    private val _version = MutableStateFlow("")
    val version: StateFlow<String> = _version


    val repository_Login = RetrofitApplication()._loginRepository

    fun getVersion() {
        runBlocking {
            viewModelScope.launch {
                _version.value = repository_Login.getVersion()
            }
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun updateLastConnection(context: Context) {
        viewModelScope.launch {
            repository_Login.updateLastConnection(context)
        }
    }

    fun setStatsProfile(context: Context) {
        viewModelScope.launch {
            repository_Login.setStatsProfile(context)
        }
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

    fun startCount(context: Context) {
        viewModelScope.launch {
            while (true) {
                val vida = getData(
                    context,
                    keyInt = PreferencesKeys.VIDA,
                    type = 2
                ).toString().toInt()
                val energia = getData(
                    context,
                    keyInt = PreferencesKeys.ENERGIA,
                    type = 2
                ).toString().toInt()
                val nivel = getData(
                    context,
                    keyInt = PreferencesKeys.NIVEL,
                    type = 2
                ).toString().toInt()

                delay(1000)
                if (vida >= (100 * nivel) && energia >= (20 * nivel)) setData(
                    context,
                    BooleanKey = PreferencesKeys.OPEN_GAME,
                    dataBoolean = false,
                    type = 4
                )
                else setData(
                    context,
                    BooleanKey = PreferencesKeys.OPEN_GAME,
                    dataBoolean = true,
                    type = 4
                )
                if (getData(
                        context,
                        keyBoolean = PreferencesKeys.OPEN_GAME,
                        type = 5
                    ) === true
                ) {
                    val previousTimePlaying = getData(
                        context,
                        keyInt = TIME_PLAYING,
                        type = 2
                    ).toString().toInt()

                    //Log.d("Tiempos", "startCount: $previousTimePlaying")

                    setData(
                        context,
                        IntKey = TIME_PLAYING,
                        dataInt = previousTimePlaying + 1,
                        type = 2
                    )
                }
            }
        }
    }


    fun getUserData(context: Context, id: String) {
        viewModelScope.launch {
            if (!finished.value && !startcount.value) {
                val value = repository_Login.getUserData(id)
                when (value) {
                    is ApiResponse.Success -> {
                        predata[0] = value.data
                        setFullDataUser(
                            context = context,
                            data = value.data,
                        )
                        finished.value = true
                        startcount.value = true
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