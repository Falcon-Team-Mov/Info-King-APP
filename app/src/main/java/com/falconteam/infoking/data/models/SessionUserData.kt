package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class SessionUserData(
    @SerializedName("_id") val id: String,
    @SerializedName("username") val username: String,
    @SerializedName("email") val email: String,
    @SerializedName("role") val role: String,
    @SerializedName("exp") val exp: Int,
    @SerializedName("nivel") val nivel: Int,
    @SerializedName("last_conection") val last_conection: String?,
    @SerializedName("creacion") val created_at: String,
    @SerializedName("time_playing") val time_playing: Int,
)
