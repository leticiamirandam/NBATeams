package com.example.nbateams.data.repository

import app.cash.turbine.test
import com.example.nbateams.data.cache.mapper.CacheToDomainMapper
import com.example.nbateams.data.cache.mapper.RemoteToCacheMapper
import com.example.nbateams.data.cache.room.TeamDao
import com.example.nbateams.data.datasource.teamslist.TeamsListRemoteDataSource
import com.example.nbateams.stubs.stubPicturesListResponse
import com.example.nbateams.stubs.stubTeamsList
import com.example.nbateams.stubs.stubTeamsListResponse
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class TeamsListRepositoryTest {

    private val teamsListRemoteDataSource = mockk<TeamsListRemoteDataSource>()
    private val teamsDao = mockk<TeamDao>()
    private val remoteToCacheMapper = RemoteToCacheMapper()
    private val cacheToDomainMapper = CacheToDomainMapper()
    private val teamsListRepository = TeamsListRepositoryImpl(
        teamsListRemoteDataSource = teamsListRemoteDataSource,
        teamsDao = teamsDao,
        remoteToCacheMapper = remoteToCacheMapper,
        cacheToDomainMapper = cacheToDomainMapper
    )

    @Test
    fun `getTeamsList Should return TeamsList When service returns`() = runBlocking {
        //Given
        val teamsListResponse = stubTeamsListResponse
        val picturesListResponse = stubPicturesListResponse
        val teamsList = stubTeamsList
        coEvery { teamsListRemoteDataSource.getTeamsPictures() } returns flow {
            emit(picturesListResponse)
        }
        coEvery { teamsListRemoteDataSource.getTeamsList() } returns flow {
            emit(teamsListResponse)
        }
        coEvery { teamsDao.insertAll(any()) } returns Unit

        //When
        val result = teamsListRepository.getTeamsList()

        //Then
        result.test {
            assertEquals(teamsList, expectItem())
            expectComplete()
        }
    }
}