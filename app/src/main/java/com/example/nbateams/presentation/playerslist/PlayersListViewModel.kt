package com.example.nbateams.presentation.playerslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class PlayersListViewModel(
    private val playersListUseCase: GetPlayersListUseCase,
    private val playerMapper: PlayerMapper,
) : ViewModel() {

    init {
        getPlayersList()
    }

    fun getPlayersList(): Flow<PagingData<PlayersList.Player>> {
        return playersListUseCase.invoke()
            .map { pagingData ->
                pagingData.map {
                    playerMapper.map(it)
                }
            }
            .cachedIn(viewModelScope)
    }

    private fun handleError() {
        //it will be implemented
    }
}