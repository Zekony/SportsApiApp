package com.example.sportsapp.data.network.dto.teamByID

data class League(
    val current_season_id: String = "",
    val id: String = "",
    val is_amateur: String = "",
    val is_cup: String = "",
    val is_friendly: String = "",
    val name: String = "",
    val seasons: List<Season> = listOf()
)