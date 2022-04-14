package com.example.nbateams.data.repository

import androidx.paging.PagingData
import com.example.nbateams.data.datasource.PlayersListRemoteDataSource
import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.domain.repository.PlayersListRepository
import kotlinx.coroutines.flow.Flow

internal class PlayersListRepositoryImpl(
    private val playersListRemoteDataSource: PlayersListRemoteDataSource,
) : PlayersListRepository {
    override fun getPlayersList(): Flow<PagingData<PlayersDetailResponse>> =
        playersListRemoteDataSource
            .getPlayersList()
}