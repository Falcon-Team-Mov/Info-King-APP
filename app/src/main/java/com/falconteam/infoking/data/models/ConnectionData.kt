package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName
import java.time.format.DateTimeFormatter

data class ConnectionData(
    @SerializedName("last_connection") val last_connection: String,
    @SerializedName("time_playing") val time_playing: Int,
)
