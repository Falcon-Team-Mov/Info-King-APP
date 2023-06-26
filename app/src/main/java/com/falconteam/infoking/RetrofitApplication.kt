package com.falconteam.infoking

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.laboratorio11.repository.CredentialsRepository
import com.falconteam.infoking.data.network.repository.CharacterRepository
import com.falconteam.infoking.data.network.retrofit.RetrofitInstance

class RetrofitApplication : Application() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        prefs = getSharedPreferences("Retrofit", Context.MODE_PRIVATE)
    }

    private fun getLoginService() = with(RetrofitInstance){
        getLoginService()
    }
    private fun getCharacterService() = with(RetrofitInstance){
        getCharacterService()
    }

    val _characterRepository: CharacterRepository by lazy {
        CharacterRepository(getCharacterService())
    }

    companion object {
        const val USER_TOKEN = "user_token"

    }
}