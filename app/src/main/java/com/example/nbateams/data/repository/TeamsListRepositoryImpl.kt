package com.example.nbateams.data.repository

import com.example.nbateams.data.cache.mapper.CacheToDomainMapper
import com.example.nbateams.data.cache.mapper.RemoteToCacheMapper
import com.example.nbateams.data.cache.room.TeamDao
import com.example.nbateams.data.datasource.TeamsListRemoteDataSource
import com.example.nbateams.data.model.response.TeamPictureResponse
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.repository.TeamsListRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.zip

internal class TeamsListRepositoryImpl(
    private val teamsListRemoteDataSource: TeamsListRemoteDataSource,
    private val teamsDao: TeamDao,
    private val remoteToCacheMapper: RemoteToCacheMapper,
    private val cacheToDomainMapper: CacheToDomainMapper
) : TeamsListRepository {

    override fun getTeamsList(): Flow<TeamsList> =
        teamsListRemoteDataSource
            .getTeamsList()
            .zip(
                teamsListRemoteDataSource.getTeamsPictures()
            ) { teams, pictures ->
                val teamsCacheList =
                    remoteToCacheMapper.mapTeamsListResponseToCM(teams, pictures.values.map {
                        it.toPictureList()
                    })
                teamsDao.insertAll(teamsCacheList)
                cacheToDomainMapper.mapTeamsListToCM(teamsCacheList)
            }.catch {
                emit(cacheToDomainMapper.mapTeamsListToCM(teamsDao.getAll()))
            }

    private fun List<String>.toPictureList(): TeamPictureResponse = TeamPictureResponse(
        id = this[0],
        picture = this[1]
    )
}