package com.falconteam.infoking.data.network.dto.character

data class CharacterRequest (
    val nombre: String,
    val buff: Number,
    val nerf:Number,
    val images: List<ImagesCharacter>
)