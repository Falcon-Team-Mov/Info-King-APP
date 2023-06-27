package com.falconteam.infoking.data.network.repository

import com.falconteam.infoking.data.network.ApiResponse
import com.falconteam.infoking.data.network.dto.character.CharacterResponse
import com.falconteam.infoking.data.network.service.CharacterService
import retrofit2.HttpException
import java.io.IOException

class CharacterRepository(private val api: CharacterService){
    suspend fun GetCharacters(): ApiResponse<CharacterResponse> {
        try {
            val response = api.getCharacters()
            return ApiResponse.DataCharacters(response)
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