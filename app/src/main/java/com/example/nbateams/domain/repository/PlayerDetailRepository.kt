package com.example.nbateams.domain.repository

import com.example.nbateams.domain.model.PlayersList
import kotlinx.coroutines.flow.Flow

internal interface PlayerDetailRepository {
    fun getPlayerDetail(id: Int): Flow<PlayersList.Player>
}