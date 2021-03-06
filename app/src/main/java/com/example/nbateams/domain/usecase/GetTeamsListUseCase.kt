package com.example.nbateams.domain.usecase

import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.repository.TeamsListRepository
import kotlinx.coroutines.flow.Flow

internal class GetTeamsListUseCase(
    private val teamListRepository: TeamsListRepository
) {
    operator fun invoke(): Flow<TeamsList> {
        return teamListRepository.getTeamsList()
    }
}