package com.example.sportsapp.presentation.players_screen

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
class PlayersScreenViewModel @Inject constructor(
    private val sportsRepository: SportsRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PlayersScreenState())
    val state = _state.asStateFlow()

    fun getTeamById(id: String) {
        viewModelScope.launch {
            val squad = sportsRepository.getTeamById(id)
            when (squad.status) {
                SimpleResponse.Status.Success -> {
                    _state.update { state ->
                        state.copy(
                            team = squad.body.data.toTeam(),
                            downloadState = DownloadState.Success
                        )
                    }
                    getCoachById(squad.body.data.coach_id.toString())
                }
                SimpleResponse.Status.Failure -> {
                    _state.update {
                        it.copy(
                            downloadState = DownloadState.Error
                        )
                    }
                }
            }
        }
    }

    private fun getCoachById(id: String) {
        viewModelScope.launch {
            val coach = sportsRepository.getCoachById(id)
            Log.d("PlayersScreenVM", "getCoachById: is called for id $id")
            when (coach.status) {
                SimpleResponse.Status.Success -> {
                    Log.d("PlayersScreenVM", "getCoachById: successfully got coach, names: ${coach.body.data.name}, ${coach.body.data.common_name}, ${coach.body.data.lastname}")
                    _state.update { list ->
                        list.copy(
                            coach = coach.body.data.toCoach()
                        )
                    }
                }
                SimpleResponse.Status.Failure -> {
                    _state.update {
                        it.copy(
                            downloadState = DownloadState.Error
                        )
                    }
                }
            }
        }
    }

    fun getSquadById(id: String) {
        viewModelScope.launch {
            val squad = sportsRepository.getTeamSquadById(id)
            when (squad.status) {
                SimpleResponse.Status.Success -> {
                    _state.update { list ->
                        list.copy(
                            playersList = squad.body.data.squad.map { it.toPlayer() },
                            downloadState = DownloadState.Success
                        )
                    }
                }
                SimpleResponse.Status.Failure -> {
                    _state.update {
                        it.copy(
                            downloadState = DownloadState.Error
                        )
                    }
                }
            }
        }
    }
}
