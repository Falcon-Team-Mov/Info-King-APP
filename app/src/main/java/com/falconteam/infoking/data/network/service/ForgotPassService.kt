package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.network.dto.forgotpass.ForgosPassRequest
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassCodeRequest
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassCodeResponse
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassEmailResponse
import com.falconteam.infoking.data.network.dto.forgotpass.ForgotPassResponse
import com.google.gson.annotations.SerializedName
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ForgotPassService {
    @POST("api/code/forgotpass")
    suspend fun ForgotPassCode(@Body data: ForgotPassCodeRequest): ForgotPassCodeResponse

    @GET("api/code/forgotpass/{code}")
    suspend fun ForgotPassValidCode(@Path("code") code: String): ForgotPassEmailResponse

    @PUT("api/users/forgotpass")
    suspend fun ForgotPassword(@Body data: ForgosPassRequest): ForgotPassResponse
}