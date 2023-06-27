package com.falconteam.infoking.data.network.service

import com.falconteam.infoking.data.network.dto.register.RegisterRequest
import com.falconteam.infoking.data.network.dto.register.RegisterResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpService {

    @POST("api/users")
    suspend fun postCharacter(@Body credentials: RegisterRequest): RegisterResponse

}