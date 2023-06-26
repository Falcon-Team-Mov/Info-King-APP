package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.network.dto.character.CharacterResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST

interface CharacterService {

    @GET("api/personaje")
    suspend fun getCharacters(): CharacterResponse

}