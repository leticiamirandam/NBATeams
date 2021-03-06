package com.example.nbateams.data.api

import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.data.model.response.PlayersListResponse
import com.example.nbateams.data.model.response.TeamDetailResponse
import com.example.nbateams.data.model.response.TeamsListResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface NBAService {

    @GET("teams")
    suspend fun getTeamsList(): TeamsListResponse

    @GET("players")
    suspend fun getPlayersList(
        @Query("page") page: Int
    ): PlayersListResponse

    @GET("players/{id}")
    suspend fun getPlayerDetail(
        @Path("id") playerId: Int,
    ): PlayersDetailResponse

    @GET("teams/{id}")
    suspend fun getTeamDetail(
        @Path("id") teamId: Int,
    ): TeamDetailResponse
}