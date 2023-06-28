package com.falconteam.infoking.data.network.dto.login

import com.falconteam.infoking.data.models.User
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("msg") val message: String,
    @SerializedName("token") val token: String,
    @SerializedName("user") val user: User

)