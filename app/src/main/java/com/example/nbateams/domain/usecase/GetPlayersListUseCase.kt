package com.example.nbateams.domain.usecase

import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.repository.PlayersListRepository
import kotlinx.coroutines.flow.Flow

internal class GetPlayersListUseCase(
    private val playersListRepository: PlayersListRepository
) {
    operator fun invoke(): Flow<PlayersList> {
        return playersListRepository.getPlayersList()
    }
}