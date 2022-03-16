package com.example.nbateams.data.repository

import com.example.nbateams.data.datasource.TeamsListRemoteDataSource
import com.example.nbateams.data.mapper.TeamsListMapper
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.repository.TeamsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class TeamsListRepositoryImpl(
    private val teamsListRemoteDataSource: TeamsListRemoteDataSource,
    private val teamsListMapper: TeamsListMapper
) : TeamsListRepository {
    override fun getTeamsList(): Flow<TeamsList> =
        teamsListRemoteDataSource
            .getTeamsList()
            .map(teamsListMapper::map)
}