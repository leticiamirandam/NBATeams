package com.example.nbateams.presentation.playerslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.nbateams.domain.model.PlayersList
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

    val playersList = MutableLiveData<PagingData<PlayersList.Player>>()
    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Boolean>()

    init {
        getPlayersList()
    }

    fun getPlayersList() {
        viewModelScope.launch {
            playersListUseCase()
                .flowOn(dispatcher)
                .onStart { isLoading.value = true }
                .catch {
                    isLoading.value = false
                    isError.value = true
                    handleError(it)
                }
                .cachedIn(viewModelScope)
                .collect {
                    isLoading.value = false
                    isError.value = false
                    playersList.value = it
                }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERRO: ", throwable.localizedMessage)
    }
}