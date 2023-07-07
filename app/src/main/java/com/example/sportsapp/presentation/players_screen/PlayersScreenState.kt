package com.example.sportsapp.presentation.players_screen

import com.example.sportsapp.domain.model.Coach
import com.example.sportsapp.domain.model.Player
import com.example.sportsapp.domain.model.Team
import com.example.sportsapp.presentation.util.DownloadState

data class PlayersScreenState(
    val downloadState: DownloadState = DownloadState.Loading,
    val playersList: List<Player> = emptyList(),
    val team: Team = Team(),
    val coach: Coach = Coach()
)