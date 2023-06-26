package com.falconteam.infoking.data.network

import com.falconteam.infoking.data.network.dto.character.CharacterResponse
import java.lang.Exception

sealed class ApiResponse<T> {

    data class Success<T>(val data: T) : ApiResponse<T>()

    data class Error<T>(val exception: Exception) : ApiResponse<T>()

    data class ErrorWithMessage<T>(val message: String) : ApiResponse<T>()

    data class DataCharacters<T>(val data: CharacterResponse) : ApiResponse<T>()

}