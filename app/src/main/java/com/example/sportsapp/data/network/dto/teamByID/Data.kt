package com.example.sportsapp.data.network.dto.teamByID

import com.example.sportsapp.domain.model.Team

data class Data(
    val coach_id: Int = 0,
    val common_name: String = "",
    val country: Country = Country(),
    val current_seasons: List<Int> = listOf(),
    val founded: String = "",
    val id: Int = 0,
    val img: String = "",
    val is_national: Int = 0,
    val kit: Any = Any(),
    val leagues: List<League> = listOf(),
    val name: String = "",
    val short_code: String = "",
    val twitter: String = "",
    val venue_id: Int = 0
) {
    fun toTeam(): Team {
        return Team(
            id, coach_id, name, img, founded
        )
    }
}