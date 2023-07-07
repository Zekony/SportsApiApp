package com.example.sportsapp.data.network

import com.example.sportsapp.common.Constants
import com.example.sportsapp.data.network.dto.coachByIdDto.CoachByIdDto
import com.example.sportsapp.data.network.dto.teamByID.TeamByIdDto
import com.example.sportsapp.data.network.dto.squadById.TeamSquadByIdDto
import com.example.sportsapp.data.network.dto.teamsByCountry.TeamsByCountryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface ApiService {

    @GET("teams/")
    suspend fun getTeamsByCountryId(
        @Query("user") user: String = Constants.USER,
        @Query("token") token: String = Constants.TOKEN,
        @Query("t") t: String = "list",
        @Query("country_id") id: String,
    ): Response<TeamsByCountryDto>

    @GET("teams/")
    suspend fun getTeamById(
        @Query("user") user: String = Constants.USER,
        @Query("token") token: String = Constants.TOKEN,
        @Query("t") t: String = "info",
        @Query("id") id: String,
    ): Response<TeamByIdDto>

    @GET("teams/")
    suspend fun getTeamSquadById(
        @Query("user") user: String = Constants.USER,
        @Query("token") token: String = Constants.TOKEN,
        @Query("t") t: String = "squad",
        @Query("id") id: String,
    ): Response<TeamSquadByIdDto>

    @GET("coaches/")
    suspend fun getCoachById(
        @Query("user") user: String = Constants.USER,
        @Query("token") token: String = Constants.TOKEN,
        @Query("t") t: String = "info",
        @Query("id") id: String,
    ): Response<CoachByIdDto>
}
