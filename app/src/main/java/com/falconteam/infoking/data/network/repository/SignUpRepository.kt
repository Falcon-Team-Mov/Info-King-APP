package com.falconteam.infoking.data.network.repository

import com.falconteam.infoking.data.models.Personaje
import com.falconteam.infoking.data.models.SignUpFormOne
import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.character.CharacterResponse
import com.falconteam.infoking.data.network.dto.register.RegisterRequest
import com.falconteam.infoking.data.network.dto.register.RegisterResponse
import com.falconteam.infoking.data.network.service.CharacterService
import com.falconteam.infoking.data.network.service.SignUpService
import retrofit2.HttpException
import java.io.IOException


class SignUpRepository(private val api: SignUpService){
    suspend fun AddAccount(data: RegisterRequest): ApiResponse<String> {
        try {
            val response = api.postCharacter(data)
            return ApiResponse.Success(response.message)
        } catch (e: HttpException) {
            if (e.code() === 404) {
                return ApiResponse.ErrorWithMessage("Error con el servidor")
            }
            return ApiResponse.Error(e)
        } catch (e: IOException) {
            return ApiResponse.Error(e)
        }
    }
}