package com.example.nbateams.domain.usecase

import androidx.paging.PagingData
import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.domain.repository.PlayersListRepository
import kotlinx.coroutines.flow.Flow

internal class GetPlayersListUseCase(
    private val playersListRepository: PlayersListRepository
) {
    operator fun invoke(): Flow<PagingData<PlayersDetailResponse>> {
        return playersListRepository.getPlayersList()
    }
}