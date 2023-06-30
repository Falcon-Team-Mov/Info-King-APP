package com.falconteam.infoking.data.models

data class Ranking(
    var position: String,
    var name: String,
    var type: String,
    var url: String,
)

val ranking = mutableListOf(
    Ranking("1","Nombre 1", "tipo", ""),
    Ranking("2","Nombre 2", "tipo", ""),
    Ranking("3","Nombre 3", "tipo", ""),
    Ranking("4","Nombre 4", "tipo", ""),
    Ranking("5","Nombre 5", "tipo", ""),
    Ranking("6","Nombre 6", "tipo", ""),
    Ranking("6","Nombre 6", "tipo", ""),
    Ranking("6","Nombre 6", "tipo", ""),
    Ranking("6","Nombre 6", "tipo", ""),

    )