package com.example.nbateams.data.datasource

import app.cash.turbine.test
import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.datasource.teamdetail.TeamDetailRemoteDataSourceImpl
import com.example.nbateams.stubs.stubTeamDetailResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class TeamDetailRemoteDataSourceTest {

    private val service: NBAService = mock()
    private val dataSource = TeamDetailRemoteDataSourceImpl(service)
    private val teamId: Int = 14

    @Test
    fun `getTeamDetail Should return TeamDetailResponse When service returns success`() {
        runBlocking {

            // Given
            val teamDetail = stubTeamDetailResponse
            whenever(service.getTeamDetail(teamId)).thenAnswer { teamDetail }

            // When
            val result = dataSource.getTeamDetail(teamId)

            // Then
            result.test {
                verify(service).getTeamDetail(teamId)
                assertEquals(teamDetail, expectItem())
                expectComplete()
            }

        }
    }

    @Test
    fun `getTeamDetail Should return an error When service returns internal server error`() =
        runBlocking {
            // Given
            val error = Throwable()
            whenever(service.getTeamDetail(teamId)).thenAnswer { throw error }

            // When
            val result = dataSource.getTeamDetail(teamId)

            // Then
            result.test {
                verify(service).getTeamDetail(teamId)
                assertEquals(error, expectError())
                expectNoEvents()
            }
        }
}