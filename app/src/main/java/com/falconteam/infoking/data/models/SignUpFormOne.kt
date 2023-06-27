package com.falconteam.infoking.data.models

import com.falconteam.infoking.data.network.dto.character.ImagesCharacter
import com.google.gson.annotations.SerializedName

data class SignUpFormOne(
    var username: String,
    var email: String,
    var password: String,
    var personaje: String
)
