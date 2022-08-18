package com.example.nbateams.presentation.teamdetail

import androidx.lifecycle.MutableLiveData
import com.example.nbateams.domain.model.TeamsList

data class TeamDetailState(
    var isLoading: Boolean = false,
    var team: MutableLiveData<TeamsList.Team> = MutableLiveData()
)