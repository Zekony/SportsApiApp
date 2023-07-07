package com.example.sportsapp.data.network.dto.coachByIdDto

import com.example.sportsapp.domain.model.Coach

data class Data(
    val birthcountry: Any = Any(),
    val birthday: String = "",
    val birthplace: Any = Any(),
    val common_name: String = "",
    val country: Country = Country(),
    val firstname: String = "",
    val id: String = "",
    val img: String = "",
    val lastname: String = "",
    val name: String = "",
    val team_id: Any = Any()
) {
    fun toCoach(): Coach {
        return Coach(id = id, name = common_name, img = img)
    }
}