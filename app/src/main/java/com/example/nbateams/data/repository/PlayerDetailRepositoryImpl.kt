package com.example.nbateams.data.repository

import com.example.nbateams.data.datasource.playerdetail.PlayerDetailRemoteDataSource
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.repository.PlayerDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PlayerDetailRepositoryImpl(
    private val playerDetailRemoteDataSource: PlayerDetailRemoteDataSource,
    private val playerDetailMapper: PlayerMapper
) : PlayerDetailRepository {
    override fun getPlayerDetail(id: Int): Flow<PlayersList.Player> =
        playerDetailRemoteDataSource
            .getPlayerDetail(id)
            .map(playerDetailMapper::map)
}