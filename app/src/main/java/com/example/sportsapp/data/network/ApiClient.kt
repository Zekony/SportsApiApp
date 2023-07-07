package com.example.sportsapp.data.network

import android.util.Log
import com.example.sportsapp.data.network.dto.coachByIdDto.CoachByIdDto
import com.example.sportsapp.data.network.dto.teamByID.TeamByIdDto
import com.example.sportsapp.data.network.dto.squadById.TeamSquadByIdDto
import com.example.sportsapp.data.network.dto.teamsByCountry.TeamsByCountryDto
import retrofit2.Response
import javax.inject.Inject

class ApiClient @Inject constructor(private val service: ApiService) {

    suspend fun getTeamsByCountry(id: String): SimpleResponse<TeamsByCountryDto> {
        return safeApiCall { service.getTeamsByCountryId(id = id) }
    }

    suspend fun getTeamById(id: String): SimpleResponse<TeamByIdDto> {
        return safeApiCall { service.getTeamById(id = id) }
    }

    suspend fun getTeamSquadById(id: String): SimpleResponse<TeamSquadByIdDto> {
        return safeApiCall { service.getTeamSquadById(id = id) }
    }

    suspend fun getCoachById(id: String): SimpleResponse<CoachByIdDto> {
        return safeApiCall { service.getCoachById(id = id) }
    }

    private inline fun <T> safeApiCall(apiCall: () -> Response<T>): SimpleResponse<T> {
        return try {
            SimpleResponse.success(apiCall.invoke())
        } catch (e: Exception) {
            Log.d("ApiClient", "Response was not successful ${e.message}")
            SimpleResponse.failure(e)
        }
    }
}