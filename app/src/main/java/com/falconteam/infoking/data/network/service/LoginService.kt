package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.models.LoginDataResponse
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface LoginService {
    @POST("api/auth/login")
    suspend fun Login(@Body credentials: LoginRequest): LoginResponse

    @GET("api/users/{id}")
    suspend fun getUserData(@Path("id") id: String): LoginDataResponse
}