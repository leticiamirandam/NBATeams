package com.example.nbateams.data.datasource

import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.model.response.PlayersListResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class PlayersListRemoteDataSourceImpl(
    private val nbaService: NBAService
): PlayersListRemoteDataSource {
    override fun getPlayersList(): Flow<PlayersListResponse> = flow {
        emit(nbaService.getPlayersList())
    }
}