package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class ProfileRankingData(
    @SerializedName("position") val position: Int,
    @SerializedName("score") val score: Int,
    @SerializedName("victorias") val victorias: Int,
    @SerializedName("derrotas") val derrotas: Int,
    @SerializedName("msg") val msg: String? = null,
)
