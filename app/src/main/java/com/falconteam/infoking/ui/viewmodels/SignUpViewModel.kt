package com.falconteam.infoking.ui.viewmodels

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.models.Personaje
import com.falconteam.infoking.data.models.SignUpFormOne
import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.register.RegisterRequest
import kotlinx.coroutines.launch

class SignUpViewModel() : ViewModel() {

    val _datosPersonajes = mutableStateOf<List<Personaje>>(listOf())
    val errors: MutableLiveData<String> = MutableLiveData()
    val retrofitApplication = RetrofitApplication()
    val msg = mutableStateOf<String>("")

    val repository_Character = retrofitApplication._characterRepository
    val repository_Register = retrofitApplication._registerRepository

    fun AddAccount(account: SignUpFormOne) {
        viewModelScope.launch {
            val value = repository_Register.AddAccount(
                RegisterRequest(
                    account.username,
                    account.email,
                    account.password,
                    account.personaje
                )
            )
            when(value){
                is ApiResponse.Success -> {
                    msg.value = value.data
                }
                is ApiResponse.Error -> {
                    msg.value = value.exception.message.toString()
                }
                is ApiResponse.ErrorWithMessage -> {
                    msg.value = value.message
                }
                else -> {
                    msg.value = "Error desconocido"
                }
            }
        }
    }

    fun obtenerPersonajes() {
        viewModelScope.launch {

            val respuesta = repository_Character.GetCharacters()
            when (respuesta) {
                is ApiResponse.DataCharacters -> {
                    _datosPersonajes.value = respuesta.data.personajes
                }

                is ApiResponse.Error -> {
                    errors.value = respuesta.exception.message
                }

                is ApiResponse.ErrorWithMessage -> {
                    errors.value = respuesta.message
                }

                else -> {
                    errors.value = "Error desconocido"
                }
            }
        }
    }
}
