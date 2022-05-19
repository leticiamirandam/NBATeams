package com.example.nbateams.presentation.playerdetail

import android.util.Log
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
) : ViewModel() {

    val playerDetail = MutableLiveData<PlayersList.Player>()
    var isLoading = MutableLiveData<Boolean>()
    var isError = MutableLiveData<Boolean>()

    init {
        getPlayerDetail()
    }

    fun getPlayerDetail() {
        viewModelScope.launch {
            getPlayerDetailUseCase(playerId)
                .flowOn(dispatcher)
                .onStart { isLoading.value = true }
                .catch {
                    isError.value = true
                    handleError(it)
                }
                .onCompletion { isLoading.value = false }
                .collect { playerDetail.value = it }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERRO: ", throwable.localizedMessage)
    }
}