package com.example.nbateams.data.repository

import com.example.nbateams.data.datasource.TeamsListRemoteDataSource
import com.example.nbateams.data.mapper.TeamsListMapper
import com.example.nbateams.data.model.response.TeamPictureResponse
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.repository.TeamsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.zip

internal class TeamsListRepositoryImpl(
    private val teamsListRemoteDataSource: TeamsListRemoteDataSource,
    private val teamsListMapper: TeamsListMapper
) : TeamsListRepository {
    override fun getTeamsList(): Flow<TeamsList> =
        teamsListRemoteDataSource
            .getTeamsList()
            .zip(
                teamsListRemoteDataSource.getTeamsPictures()
            ) { teams, pictures ->
                teamsListMapper.map(teams, pictures.values.map {
                    it.toPictureList()
                })
            }

    fun List<String>.toPictureList(): TeamPictureResponse = TeamPictureResponse(
        id = this[0],
        picture = this[1]
    )
}