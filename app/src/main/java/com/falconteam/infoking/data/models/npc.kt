package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class npc(
    @SerializedName("nombre") val nombre: String,
    @SerializedName("vida") var vida: Int,
    @SerializedName("ataque") val ataque: Int,
    @SerializedName("defensa") val defensa: Int,
    @SerializedName("url") val imagen: String,
)
