package com.example.nbateams.presentation.teamslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbateams.domain.usecase.GetTeamsListUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

internal class TeamsListViewModel(
    private val getTeamsListUseCase: GetTeamsListUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    init {
        getTeamsList()
    }

    fun getTeamsList() {
        viewModelScope.launch {
//            getTeamsListUseCase
//                .flowOn(dispatcher)
//                .onStart()
//                .catch { handleError() }
//                .onCompletion()
//                .collect { handleSuccess() }
        }
    }

    fun handleSuccess() {

    }

    fun handleError() {

    }
}