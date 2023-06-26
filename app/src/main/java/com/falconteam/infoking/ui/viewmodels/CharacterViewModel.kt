package com.falconteam.infoking.ui.viewmodels

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.falconteam.infoking.RetrofitApplication
import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.character.CharacterResponse
import com.falconteam.infoking.data.network.repository.CharacterRepository
import kotlinx.coroutines.launch
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import kotlin.reflect.typeOf

class CharacterViewModel() : ViewModel() {

    private val _datosPersonajes = MutableLiveData<CharacterResponse>()
    val datosPersonajes: LiveData<CharacterResponse> = _datosPersonajes
    val errors: MutableLiveData<String> = MutableLiveData()
    val retrofitApplication = RetrofitApplication()

    val repository = retrofitApplication._characterRepository


    fun obtenerPersonajes() {
        viewModelScope.launch {

            val respuesta = repository.GetCharacters()
            when (respuesta) {
                is ApiResponse.DataCharacters -> {
                    _datosPersonajes.value = respuesta.data
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
