package com.example.nbateams.data.datasource

import com.example.nbateams.data.model.response.TeamDetailResponse
import kotlinx.coroutines.flow.Flow

internal interface TeamDetailRemoteDataSource {
    fun getTeamDetail(id: Int): Flow<TeamDetailResponse>
}