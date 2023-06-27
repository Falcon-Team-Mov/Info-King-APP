package com.falconteam.infoking.data.models

import com.falconteam.infoking.data.network.dto.character.ImagesCharacter
import com.google.gson.annotations.SerializedName

data class Personaje(
    @SerializedName("_id") var id: String,
    @SerializedName("nombre") var nombre: String,
    @SerializedName("images") var images: ImagesCharacter,
    @SerializedName("buff") var buff: Double,
    @SerializedName("nerf") var nerf:Double,
)

