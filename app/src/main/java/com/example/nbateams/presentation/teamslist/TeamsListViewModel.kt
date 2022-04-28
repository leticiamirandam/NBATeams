package com.example.nbateams.presentation.teamslist

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.usecase.GetTeamsListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class TeamsListViewModel(
    private val getTeamsListUseCase: GetTeamsListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var teamsListResult = MutableLiveData<TeamsList>()
    var isLoading: Boolean = false

    init {
        getTeamsList()
    }

    private fun getTeamsList() {
        viewModelScope.launch {
            getTeamsListUseCase()
                .flowOn(dispatcher)
                .onStart { isLoading = true }
                .catch { handleError(it) }
                .onCompletion { isLoading = false }
                .collect { teamsListResult.value = it }
        }
    }

    fun handleError(throwable: Throwable) {
        Log.i("ERRO: ", throwable.localizedMessage)
    }
}