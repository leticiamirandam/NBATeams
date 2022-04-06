package com.example.nbateams.domain.usecase

import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.repository.TeamDetailRepository
import kotlinx.coroutines.flow.Flow

internal class GetTeamDetailUseCase(
    private val teamDetailRepository: TeamDetailRepository
) {
    operator fun invoke(id: Int): Flow<TeamsList.Team> {
        return teamDetailRepository.getTeamDetail(id)
    }
}