package com.example.nbateams.presentation.playerslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class PlayersListViewModel(
    private val playersListUseCase: GetPlayersListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val playersListState = MutableLiveData<PlayersListState>()
    var isError = MutableLiveData<Boolean>()

    init {
        getPlayersList()
    }

    fun getPlayersList() {
        viewModelScope.launch {
            playersListUseCase()
                .flowOn(dispatcher)
                .onStart {
                    playersListState.value =
                        PlayersListState(players = PagingData.empty(), isLoading = true)
                }
                .catch {
                    playersListState.value =
                        PlayersListState(players = PagingData.empty(), isLoading = false)
                    isError.value = true
                    handleError(it)
                }
                .cachedIn(viewModelScope)
                .collect {
                    playersListState.value = PlayersListState(players = it, isLoading = false)
                    isError.value = false
                }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERRO: ", throwable.localizedMessage)
    }
}