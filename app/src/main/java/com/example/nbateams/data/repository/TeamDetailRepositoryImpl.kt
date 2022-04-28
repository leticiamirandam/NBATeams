package com.example.nbateams.data.repository

import com.example.nbateams.data.datasource.TeamDetailRemoteDataSource
import com.example.nbateams.data.mapper.TeamMapper
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.repository.TeamDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TeamDetailRepositoryImpl(
    private val teamDetailRemoteDataSource: TeamDetailRemoteDataSource,
    private val teamDetailMapper: TeamMapper
) : TeamDetailRepository {
    override fun getTeamDetail(id: Int): Flow<TeamsList.Team> =
        teamDetailRemoteDataSource
            .getTeamDetail(id)
            .map { teamDetailMapper.map(it, null) }
}