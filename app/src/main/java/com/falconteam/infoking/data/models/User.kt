package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("name") var username: String,
    @SerializedName("email")var email: String,
    @SerializedName("role") var role: String,
)
