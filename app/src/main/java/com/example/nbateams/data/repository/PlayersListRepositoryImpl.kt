package com.example.nbateams.data.repository

import com.example.nbateams.data.datasource.PlayersListRemoteDataSource
import com.example.nbateams.data.mapper.PlayersListMapper
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.repository.PlayersListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PlayersListRepositoryImpl(
    private val playersListRemoteDataSource: PlayersListRemoteDataSource,
    private val playersListMapper: PlayersListMapper
) : PlayersListRepository {
    override fun getPlayersList(): Flow<PlayersList> =
        playersListRemoteDataSource
            .getPlayersList()
            .map(playersListMapper::map)
}