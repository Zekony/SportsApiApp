package com.example.sportsapp.data.network.dto.squadById

import com.example.sportsapp.domain.model.Player

data class Squad(
    val captain: Any? = Any(),
    val number: String? = "",
    val order: Any? = Any(),
    val player: PlayerDto = PlayerDto(),
    val position: String? = ""
) {
    fun toPlayer(): Player {
        return Player(
            name = player.common_name ?: "Addis Abeba",
            id = player.id ?: "",
            img = player.img ?: "",
            position = position ?: "A"
        )
    }
}