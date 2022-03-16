package com.example.nbateams.domain.repository

import com.example.nbateams.domain.model.PlayersList
import kotlinx.coroutines.flow.Flow

internal interface PlayersListRepository {
    fun getPlayersList(): Flow<PlayersList>
}