package com.example.nbateams.presentation.playerdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.usecase.GetPlayerDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class PlayerDetailViewModel(
    private val playerId: Int,
    private val getPlayerDetailUseCase: GetPlayerDetailUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
): ViewModel() {

    val playerDetail = MutableLiveData<PlayersList.Player>()
    var isLoading: Boolean = false

    init {
        getPlayerDetail()
    }

    private fun getPlayerDetail(){
        viewModelScope.launch {
            getPlayerDetailUseCase(playerId)
                .flowOn(dispatcher)
                .onStart { isLoading = true }
                .catch { handleError() }
                .onCompletion { isLoading = false }
                .collect { playerDetail.value = it }
        }
    }

    private fun handleError() {
        //it will be implemented
    }
}