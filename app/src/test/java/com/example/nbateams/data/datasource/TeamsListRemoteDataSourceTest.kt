package com.example.nbateams.data.datasource

import app.cash.turbine.test
import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.api.PictureService
import com.example.nbateams.data.datasource.teamslist.TeamsListRemoteDataSourceImpl
import com.example.nbateams.stubs.stubTeamsListResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class TeamsListRemoteDataSourceTest {

    private val service: NBAService = mock()
    private val pictureService: PictureService = mock()
    private val dataSource = TeamsListRemoteDataSourceImpl(service, pictureService)

    @Test
    fun `getTeamsList Should return TeamsListResponse When service returns success`() {
        runBlocking {

            // Given
            val teamsList = stubTeamsListResponse
            whenever(service.getTeamsList()).thenAnswer { teamsList }

            // When
            val result = dataSource.getTeamsList()

            // Then
            result.test {
                verify(service).getTeamsList()
                assertEquals(teamsList, expectItem())
                expectComplete()
            }

        }
    }

    @Test
    fun `getTeamsList Should return an error When service returns internal server error`() =
        runBlocking {
            // Given
            val error = Throwable()
            whenever(service.getTeamsList()).thenAnswer { throw error }

            // When
            val result = dataSource.getTeamsList()

            // Then
            result.test {
                verify(service).getTeamsList()
                assertEquals(error, expectError())
                expectNoEvents()
            }
        }
}