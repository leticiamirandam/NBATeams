package com.example.nbateams.data.datasource

import app.cash.turbine.test
import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.datasource.playerslist.PlayersListRemoteDataSourceImpl
import com.example.nbateams.stubs.stubPlayersListPagingDataResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlayersListRemoteDataSourceTest {

    private val service: NBAService = mock()
    private val dataSource = PlayersListRemoteDataSourceImpl(service)
    private val pageNumber: Int = 1

    @Test
    fun `getPlayersList Should return PlayersListResponse When service returns success`() {
        runBlocking {

            // Given
            val playersList = stubPlayersListPagingDataResponse
            whenever(service.getPlayersList(pageNumber)).thenAnswer { playersList }

            // When
            val result = dataSource.getPlayersList()

            // Then
            result.test {
                verify(service).getPlayersList(pageNumber)
                assertEquals(playersList, expectItem())
                expectComplete()
            }

        }
    }

    @Test
    fun `getPlayersList Should return an error When service returns internal server error`() =
        runBlocking {
            // Given
            val error = Throwable()
            whenever(service.getPlayersList(pageNumber)).thenAnswer { throw error }

            // When
            val result = dataSource.getPlayersList()

            // Then
            result.test {
                verify(service).getPlayersList(pageNumber)
                assertEquals(error, expectError())
                expectNoEvents()
            }
        }
}