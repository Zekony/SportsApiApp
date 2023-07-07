package com.example.sportsapp.presentation.teams_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.sportsapp.data.network.SimpleResponse
import com.example.sportsapp.data.repository.SportsRepository
import com.example.sportsapp.presentation.util.DownloadState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamsScreenViewModel @Inject constructor(
    private val sportsRepository: SportsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(TeamsScreenState())
    val state = _state.asStateFlow()

    fun getTeamsIdByCountryId(id: String) {
        viewModelScope.launch {
            val idsList = sportsRepository.getTeamsByCountryId(id)
            when (idsList.status) {
                SimpleResponse.Status.Success -> {
                    _state.update { list ->
                        list.copy(
                            teamsIdsList = idsList.body.data.map { it.id.toString() }
                        )
                    }
                }
                SimpleResponse.Status.Failure -> {
                    _state.update {
                        it.copy(
                            downloadState = DownloadState.Error
                        )
                    }
                    Log.d("TeamsScreenVM", "getTeamsByCountryId: request failed for id $id")
                }
            }
            getTeamsByTheirId()
        }
    }

    private fun getTeamsByTheirId() {
        viewModelScope.launch {
            state.value.teamsIdsList.forEach { getTeamById(it) }
            _state.update {
                it.copy(
                    downloadState = DownloadState.Success
                )
            }
        }
    }

    private fun getTeamById(id: String) {
        viewModelScope.launch {
            val team = sportsRepository.getTeamById(id)

            when (team.status) {
                SimpleResponse.Status.Success -> {
                    _state.update {
                        it.copy(
                            teamsList = state.value.teamsList.plus(team.body.data.toTeam())
                        )
                    }
                }
                SimpleResponse.Status.Failure -> {
                    Log.d("TeamsScreenVM", "getTeamById: was unable to get data for id $id")
                }
            }
        }
    }
}