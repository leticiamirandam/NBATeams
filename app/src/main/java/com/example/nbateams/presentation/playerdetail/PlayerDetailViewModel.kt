package com.example.nbateams.presentation.playerdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbateams.domain.usecase.GetPlayerDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class PlayerDetailViewModel(
    private val playerId: Int,
    private val getPlayerDetailUseCase: GetPlayerDetailUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var playerDetailState = MutableLiveData<PlayerDetailState>()
    var isError = MutableLiveData<Boolean>()

    init {
        getPlayerDetail()
    }

    fun getPlayerDetail() {
        viewModelScope.launch {
            getPlayerDetailUseCase(playerId)
                .flowOn(dispatcher)
                .onStart { playerDetailState.value = PlayerDetailState(isLoading = true) }
                .catch {
                    playerDetailState.value = PlayerDetailState(isLoading = true)
                    isError.value = true
                    handleError(it)
                }
                .collect {
                    playerDetailState.value =
                        PlayerDetailState(isLoading = false, player = MutableLiveData(it))
                }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERRO: ", throwable.localizedMessage)
    }
}