package com.example.nbateams.data.datasource

import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.model.response.PlayersDetailResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PlayerDetailRemoteDataSourceImpl(
    private val nbaService: NBAService
): PlayerDetailRemoteDataSource{
    override fun getPlayerDetail(id: Int): Flow<PlayersDetailResponse> = flow {
        emit(nbaService.getPlayerDetail(id))
    }
}