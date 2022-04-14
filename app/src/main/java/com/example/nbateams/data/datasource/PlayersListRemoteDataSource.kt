package com.example.nbateams.data.datasource

import androidx.paging.PagingData
import com.example.nbateams.data.model.response.PlayersDetailResponse
import kotlinx.coroutines.flow.Flow

internal interface PlayersListRemoteDataSource {
    fun getPlayersList(): Flow<PagingData<PlayersDetailResponse>>
}