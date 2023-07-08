package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class RankingUserInfo(
    @SerializedName("id") var id: String,
    @SerializedName("username") var username: String,
    @SerializedName("icon") var icon: String,
    @SerializedName("nivel") var nivel: Int,
    @SerializedName("ataque") var ataque: Int,
    @SerializedName("defensa") var defensa: Int,
    @SerializedName("victorias") var victorias: Int,
    @SerializedName("derrotas") var derrotas: Int,
)
