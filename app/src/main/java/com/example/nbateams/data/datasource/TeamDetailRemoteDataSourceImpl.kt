package com.example.nbateams.data.datasource

import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.model.response.TeamDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TeamDetailRemoteDataSourceImpl(
    private val nbaService: NBAService
) : TeamDetailRemoteDataSource {
    override fun getTeamDetail(id: Int): Flow<TeamDetailResponse> = flow {
        emit(nbaService.getTeamDetail(id))
    }
}