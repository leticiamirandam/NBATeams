package com.example.nbateams.presentation.teamslist

import com.example.nbateams.domain.model.TeamsList

data class TeamListState(
    var isLoading: Boolean = false,
    var teamsList: MutableList<TeamsList.Team> = mutableListOf()
)