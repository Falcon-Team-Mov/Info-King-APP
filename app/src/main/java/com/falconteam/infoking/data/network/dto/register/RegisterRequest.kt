package com.falconteam.infoking.data.network.dto.register

data class RegisterRequest (
    val username: String,
    val email: String,
    val password: String,
    val personaje: String,
)