package com.example.nbateams.domain.repository

import androidx.paging.PagingData
import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.domain.model.PlayersList
import kotlinx.coroutines.flow.Flow

internal interface PlayersListRepository {
    fun getPlayersList(): Flow<PagingData<PlayersDetailResponse>>
}