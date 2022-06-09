package com.example.nbateams.data.repository

import com.example.nbateams.data.cache.mapper.CacheToDomainMapper
import com.example.nbateams.data.cache.room.TeamDao
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.repository.TeamDetailRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

internal class TeamDetailRepositoryImpl(
    private val teamsDao: TeamDao,
    private val cacheToDomainMapper: CacheToDomainMapper
) : TeamDetailRepository {
    override fun getTeamDetail(id: Int): Flow<TeamsList.Team> =
        flow { emit(cacheToDomainMapper.mapTeamToCM(teamsDao.getTeamById(id))) }
}