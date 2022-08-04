package com.example.nbateams.presentation.teamslist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.example.nbateams.domain.model.TeamsList

@Composable
    fun TeamsListContent(teams: MutableList<TeamsList.Team>){
    LazyColumn {
        items(
            items = teams,
            itemContent = { TeamListItem(team = it) }
        )
    }
}