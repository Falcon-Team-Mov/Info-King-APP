package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.network.dto.register.RegisterRequest
import com.falconteam.infoking.data.network.dto.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST


interface AuthService {

//    @POST("api/auth/login")
//    suspend fun login(@Body credentials: LoginRequest): LoginResponse

    @POST("api/users")
    suspend fun register(@Body credentials: RegisterRequest): RegisterResponse
}