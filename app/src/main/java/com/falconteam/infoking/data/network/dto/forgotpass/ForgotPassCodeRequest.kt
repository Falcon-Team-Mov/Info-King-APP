package com.falconteam.infoking.data.network.dto.forgotpass

import com.google.gson.annotations.SerializedName

data class ForgotPassCodeRequest(
    @SerializedName("email") val email: String
)