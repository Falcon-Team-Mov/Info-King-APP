package com.falconteam.infoking.data.network.retrofit

import com.falconteam.infoking.data.network.service.AuthService
import com.falconteam.infoking.data.network.service.CharacterService
import com.falconteam.infoking.data.network.service.LoginService
import com.falconteam.infoking.data.network.service.SignUpService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://api.infoking.tech/"

object RetrofitInstance {

    private var token = ""

    fun setToken(token: String){
        this.token = token
    }

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getCharacterService(): CharacterService {
        return retrofit.create(CharacterService::class.java)
    }

    fun getLoginService(): LoginService {
        return retrofit.create(LoginService::class.java)
    }

    fun getSignUpService(): SignUpService {
        return retrofit.create(SignUpService::class.java)
    }
}