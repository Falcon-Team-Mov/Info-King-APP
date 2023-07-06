package com.falconteam.infoking.data.network.dto.ranking

import com.google.gson.annotations.SerializedName

data class RankingRequest(
    @SerializedName("cuenta_id") val cuenta: String,
    @SerializedName("personaje_id") val personaje: String,
    @SerializedName("id_npc") val npc: String? = null,
)