package com.example.sportsapp.data.repository

import com.example.sportsapp.data.network.ApiClient
import com.example.sportsapp.data.network.SimpleResponse
import com.example.sportsapp.data.network.dto.coachByIdDto.CoachByIdDto
import com.example.sportsapp.data.network.dto.teamByID.TeamByIdDto
import com.example.sportsapp.data.network.dto.squadById.TeamSquadByIdDto
import com.example.sportsapp.data.network.dto.teamsByCountry.TeamsByCountryDto
import javax.inject.Inject

class SportsRepository @Inject constructor(
    private val api: ApiClient
) {
    suspend fun getTeamsByCountryId(id: String): SimpleResponse<TeamsByCountryDto> {
        return api.getTeamsByCountry(id)
    }

    suspend fun getTeamById(id: String): SimpleResponse<TeamByIdDto> {
        return api.getTeamById(id)
    }

    suspend fun getTeamSquadById(id: String): SimpleResponse<TeamSquadByIdDto> {
        return api.getTeamSquadById(id)
    }

    suspend fun getCoachById(id: String): SimpleResponse<CoachByIdDto> {
        return api.getCoachById(id)
    }
}