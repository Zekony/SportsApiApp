package com.example.sportsapp.presentation.teams_screen

import com.example.sportsapp.domain.model.Team
import com.example.sportsapp.presentation.util.DownloadState

data class TeamsScreenState(
    val downloadState: DownloadState = DownloadState.Loading,
    val teamsList: List<Team> = emptyList(),
    val teamsIdsList: List<String> = emptyList()
)
