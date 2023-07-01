package com.falconteam.infoking.data.network.dto.login

import com.falconteam.infoking.data.models.SessionPersonajeData
import com.falconteam.infoking.data.models.SessionPoderesData
import com.falconteam.infoking.data.models.SessionStatsData
import com.falconteam.infoking.data.models.SessionUserData
import com.google.gson.annotations.SerializedName

data class LoginResponse(
    @SerializedName("user") val user: SessionUserData,
    @SerializedName("token") val token: String,
    @SerializedName("stats") val stats: SessionStatsData,
    @SerializedName("Personaje") val personaje: SessionPersonajeData,
    @SerializedName("Poderes") val poderes: List<SessionPoderesData>
)