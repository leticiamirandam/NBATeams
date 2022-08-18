package com.example.nbateams.presentation.teamdetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.nbateams.domain.usecase.GetTeamDetailUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

internal class TeamDetailViewModel(
    private val teamId: Int,
    private val getTeamDetailUseCase: GetTeamDetailUseCase,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {

    var teamDetailState = MutableLiveData<TeamDetailState>()
    var isError = MutableLiveData<Boolean>()

    init {
        getTeamDetail()
    }

    fun getTeamDetail() {
        viewModelScope.launch {
            getTeamDetailUseCase(teamId)
                .flowOn(dispatcher)
                .onStart {
                    teamDetailState.value = TeamDetailState(isLoading = true)
                }
                .catch {
                    teamDetailState.value = TeamDetailState(isLoading = false)
                    isError.value = true
                    handleError(it)
                }
                .collect {
                    teamDetailState.value = TeamDetailState(
                        isLoading = false,
                        team = MutableLiveData(it)
                    )
                }
        }
    }

    private fun handleError(throwable: Throwable) {
        Log.i("ERRO: ", throwable.localizedMessage)
    }
}