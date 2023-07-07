package com.example.sportsapp.data.network.dto.squadById

data class PlayerDto(
    val age: Int = 0,
    val common_name: String? = "",
    val country: Country = Country(),
    val firstname: String = "",
    val height: String = "",
    val id: String? = "",
    val img: String? = "",
    val lastname: String = "",
    val name: Any = Any(),
    val weight: String = ""
)