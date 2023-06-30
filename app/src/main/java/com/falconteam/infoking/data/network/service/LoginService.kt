package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginService {
    @POST("api/auth/login")
    suspend fun Login(@Body credentials: LoginRequest): LoginResponse
}