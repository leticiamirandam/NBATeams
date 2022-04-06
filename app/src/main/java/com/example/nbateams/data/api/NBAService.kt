package com.example.nbateams.data.api

import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.data.model.response.PlayersListResponse
import com.example.nbateams.data.model.response.TeamsListResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface NBAService {

    @GET("teams")
    suspend fun getTeamsList(): TeamsListResponse

    @GET("players")
    suspend fun getPlayersList(): PlayersListResponse

    @GET("players/{id}")
    suspend fun getPlayerDetail(
        @Path("id") playerId: Int,
    ): PlayersDetailResponse
}