package com.example.nbateams.data.datasource

import com.example.nbateams.data.model.response.PlayersListResponse
import kotlinx.coroutines.flow.Flow

internal interface PlayersListRemoteDataSource {
    fun getPlayersList(): Flow<PlayersListResponse>
}