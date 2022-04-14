package com.example.nbateams.data.repository

import androidx.paging.PagingData
import com.example.nbateams.data.datasource.PlayersListRemoteDataSource
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.repository.PlayersListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PlayersListRepositoryImpl(
    private val playersListRemoteDataSource: PlayersListRemoteDataSource,
    private val playerMapper: PlayerMapper
) : PlayersListRepository {
    override fun getPlayersList(): Flow<PagingData<PlayersList.Player>> =
        playersListRemoteDataSource
            .getPlayersList()
            .map(playerMapper::mapPlayerListItem)
}