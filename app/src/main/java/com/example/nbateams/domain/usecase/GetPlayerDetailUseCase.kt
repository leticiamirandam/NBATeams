package com.example.nbateams.domain.usecase

import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.repository.PlayerDetailRepository
import kotlinx.coroutines.flow.Flow

internal class GetPlayerDetailUseCase(
    private val playerDetailRepository: PlayerDetailRepository
) {
    operator fun invoke(id: Int): Flow<PlayersList.Player> {
        return playerDetailRepository.getPlayerDetail(id)
    }
}