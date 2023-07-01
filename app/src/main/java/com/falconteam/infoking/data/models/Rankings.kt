package com.falconteam.infoking.data.models

import com.google.gson.annotations.SerializedName

data class Rankings(
    @SerializedName("id") var id: String,
    @SerializedName("username") var username: String,
    @SerializedName("icon") var icon: String,
    @SerializedName("victorias") var victorias: Int,
    @SerializedName("derrotas") var derrotas: Int,
)
