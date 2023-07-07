package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class StatsProfileData(
    @SerializedName("vida") val vida: Int,
    @SerializedName("energia") val energia: Int,
)
