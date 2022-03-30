package com.example.nbateams.presentation.playerslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class PlayersListViewModel(
    private val getPlayersListUseCase: GetPlayersListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val playersListResponse = MutableLiveData<PlayersList>()
    var isLoading: Boolean = false

    init {
        getPlayersList()
    }

    private fun getPlayersList() {
        viewModelScope.launch {
            getPlayersListUseCase()
                .flowOn(dispatcher)
                .onStart { isLoading = true }
                .catch { handleError() }
                .onCompletion { isLoading = false }
                .collect { playersListResponse.value = it }
        }
    }

    private fun handleError() {
        //it will be implemented
    }
}