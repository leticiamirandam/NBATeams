package com.example.nbateams.presentation.teamdetail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.usecase.GetTeamDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

internal class TeamDetailViewModel(
    private val teamId: Int,
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    val teamDetail = MutableLiveData<TeamsList.Team>()
    var isLoading: Boolean = false

    init {
        getTeamDetail()
    }

    private fun getTeamDetail() {
        viewModelScope.launch {
            getTeamDetailUseCase(teamId)
                .flowOn(dispatcher)
                .onStart { isLoading = true }
                .catch { handleError() }
                .onCompletion { isLoading = false }
                .collect { teamDetail.value = it }
        }
    }

    private fun handleError() {
        //it will be implemented
    }
}