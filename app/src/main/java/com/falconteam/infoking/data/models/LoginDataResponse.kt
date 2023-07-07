package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class LoginDataResponse(
    @SerializedName("_id") val id: String,
    @SerializedName("username") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("exp") val exp: Int,
    @SerializedName("nivel") val nivel: Int,
    @SerializedName("creacion") val creacion: String,
    @SerializedName("last_conection") val last_conection: String,
    @SerializedName("time_playing") val time_playing: Int,
    @SerializedName("stats") val stats: SessionStatsData,
    @SerializedName("msg") val msg: String = "",
    @SerializedName("images_profile") val img: String
)