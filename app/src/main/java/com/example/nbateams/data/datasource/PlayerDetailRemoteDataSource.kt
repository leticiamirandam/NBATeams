package com.example.nbateams.data.datasource

import com.example.nbateams.data.model.response.PlayersDetailResponse
import kotlinx.coroutines.flow.Flow

internal interface PlayerDetailRemoteDataSource {
    fun getPlayerDetail(id: Int): Flow<PlayersDetailResponse>
}