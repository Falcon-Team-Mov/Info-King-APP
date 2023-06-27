package com.falconteam.infoking.ui.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.network.ApiResponse
import kotlinx.coroutines.launch
import androidx.compose.runtime.mutableStateOf
import com.falconteam.infoking.data.models.Personaje

class CharacterViewModel() : ViewModel() {

    val _datosPersonajes = mutableStateOf<List<Personaje>>(listOf())
    val errors: MutableLiveData<String> = MutableLiveData()
    val retrofitApplication = RetrofitApplication()

    val repository = retrofitApplication._characterRepository

    fun agregarPersonaje(personaje: Personaje) {

    }
    fun obtenerPersonajes() {
        viewModelScope.launch {

            val respuesta = repository.GetCharacters()
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
