package com.falconteam.infoking.data.network.dto.character

import com.falconteam.infoking.data.models.Personaje
import com.google.gson.annotations.SerializedName

data class CharacterResponse(
    @SerializedName("total") val total: Int,
    @SerializedName("personajes") val personajes: List<Personaje>
)