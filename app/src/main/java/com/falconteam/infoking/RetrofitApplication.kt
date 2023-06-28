package com.falconteam.infoking

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import com.example.laboratorio11.repository.CredentialsRepository
import com.falconteam.infoking.data.network.repository.CharacterRepository
import com.falconteam.infoking.data.network.repository.LoginRepository
import com.falconteam.infoking.data.network.repository.SignUpRepository
import com.falconteam.infoking.data.network.retrofit.RetrofitInstance

class RetrofitApplication : Application() {
    private lateinit var prefs: SharedPreferences

    override fun onCreate() {
        super.onCreate()

        prefs = getSharedPreferences("Retrofit", Context.MODE_PRIVATE)
    }
    private fun getCharacterService() = with(RetrofitInstance){
        getCharacterService()
    }

    private fun getLoginService() = with(RetrofitInstance){
        getLoginService()
    }
    private fun getRegisterService() = with(RetrofitInstance){
        getSignUpService()
    }

    val _characterRepository: CharacterRepository by lazy {
        CharacterRepository(getCharacterService())
    }

    val _loginRepository: LoginRepository by lazy {
        LoginRepository(getLoginService())
    }

    val _registerRepository: SignUpRepository by lazy {
        SignUpRepository(getRegisterService())
    }

    companion object {
        const val USER_TOKEN = "user_token"

    }
}