package com.falconteam.infoking.data.models

import com.falconteam.infoking.data.network.dto.character.ImagesCharacter
import com.google.gson.annotations.SerializedName

data class SessionPersonajeData(
    val nombre: String,
    val buff: Double,
    val nerf: Double,
    @SerializedName("images") val image:ImagesCharacter
)
