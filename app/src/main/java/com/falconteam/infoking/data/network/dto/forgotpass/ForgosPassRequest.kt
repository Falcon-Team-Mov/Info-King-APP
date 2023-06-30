package com.falconteam.infoking.data.network.dto.forgotpass

import com.google.gson.annotations.SerializedName

data class ForgosPassRequest(
    @SerializedName("email") val email: String,
    @SerializedName("password") val password: String
)
