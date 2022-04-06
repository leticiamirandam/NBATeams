package com.example.nbateams.domain.repository

import com.example.nbateams.domain.model.TeamsList
import kotlinx.coroutines.flow.Flow

internal interface TeamDetailRepository {
    fun getTeamDetail(id: Int): Flow<TeamsList.Team>
}