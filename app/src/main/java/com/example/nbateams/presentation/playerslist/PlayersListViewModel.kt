package com.example.nbateams.presentation.playerslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class PlayersListViewModel(
    private val playersListUseCase: GetPlayersListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val playersList = MutableLiveData<PagingData<PlayersList.Player>>()

    init {
        getPlayersList()
    }

    private fun getPlayersList() {
        viewModelScope.launch {
            playersListUseCase.invoke()
                .flowOn(dispatcher)
                .cachedIn(viewModelScope)
                .collectLatest {
                    handleSuccess(it)
                }
        }
    }

    private fun handleSuccess(players: PagingData<PlayersList.Player>) {
        playersList.value = players
    }

    private fun handleError() {
        //it will be implemented
    }
}