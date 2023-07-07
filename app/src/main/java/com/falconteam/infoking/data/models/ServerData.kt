package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class ServerData(
    @SerializedName("version") val version: String
)
