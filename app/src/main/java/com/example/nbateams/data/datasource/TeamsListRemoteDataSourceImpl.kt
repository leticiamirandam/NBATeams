package com.example.nbateams.data.datasource

import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.model.response.TeamsListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TeamsListRemoteDataSourceImpl(
    private val nbaService: NBAService
) : TeamsListRemoteDataSource {
    override fun getTeamsList(): Flow<TeamsListResponse> = flow {
        emit(nbaService.getTeamsList())
    }
}