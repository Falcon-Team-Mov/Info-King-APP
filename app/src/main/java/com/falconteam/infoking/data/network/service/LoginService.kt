package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.models.ConnectionData
import com.falconteam.infoking.data.models.LoginDataResponse
import com.falconteam.infoking.data.models.ServerData
import com.falconteam.infoking.data.models.StatsProfileData
import com.falconteam.infoking.data.network.dto.login.LoginRequest
import com.falconteam.infoking.data.network.dto.login.LoginResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LoginService {
    @POST("api/auth/login")
    suspend fun Login(@Body credentials: LoginRequest): LoginResponse

    @GET("api/users/{id}")
    suspend fun getUserData(@Path("id") id: String): LoginDataResponse

    @GET("api/server")
    suspend fun getVersion(): ServerData

    @PUT("api/users/connection/{id}")
    suspend fun setConnection(@Path("id") id: String, @Body data: ConnectionData)

    @PUT("api/stats/statsprofile/{id}")
    suspend fun setStatsProfile(@Path("id") id: String, @Body data: StatsProfileData)

    @PUT("api/stats/max/{id}")
    suspend fun setMaxStatsProfile(@Path("id") id: String, @Body data: StatsProfileData)
}