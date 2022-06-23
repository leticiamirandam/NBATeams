package com.example.nbateams.data.repository

import app.cash.turbine.test
import com.example.nbateams.data.cache.mapper.CacheToDomainMapper
import com.example.nbateams.data.cache.room.TeamDao
import com.example.nbateams.data.datasource.teamdetail.TeamDetailRemoteDataSource
import com.example.nbateams.stubs.stubTeamCM
import com.example.nbateams.stubs.stubTeamDetailResponse
import com.example.nbateams.stubs.stubTeamDetailWithPicture
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class TeamDetailRepositoryTest {

    private val teamDetailRemoteDataSource = mockk<TeamDetailRemoteDataSource>()
    private val teamsDao = mockk<TeamDao>()
    private val cacheToDomainMapper = CacheToDomainMapper()
    private val teamId: Int = 14
    private val teamDetailRepository = TeamDetailRepositoryImpl(
        teamsDao = teamsDao,
        cacheToDomainMapper = cacheToDomainMapper
    )

    @Test
    fun `getTeamDetail Should return Team When service returns`() = runBlocking {
        //Given
        val teamDetailResponse = stubTeamDetailResponse
        val teamDetail = stubTeamDetailWithPicture
        val teamDetailCM = stubTeamCM
        coEvery { teamDetailRemoteDataSource.getTeamDetail(teamId) } returns flow {
            emit(
                teamDetailResponse
            )
        }
        coEvery { teamsDao.getTeamById(teamId) } returns teamDetailCM

        //When
        val result = teamDetailRepository.getTeamDetail(teamId)

        //Then
        result.test {
            assertEquals(teamDetail, expectItem())
            expectComplete()
        }
    }
}