package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class Login(
    @SerializedName("email") var username: String,
    @SerializedName("password")var password: String,
)
