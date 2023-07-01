package com.falconteam.infoking.data.network.dto.character

import com.google.gson.annotations.SerializedName

data class ImagesCharacter(
    @SerializedName("2d") var _2d: String?,
    @SerializedName("3d") var _3d: String?,
    @SerializedName("icon") var icon: String?,
)