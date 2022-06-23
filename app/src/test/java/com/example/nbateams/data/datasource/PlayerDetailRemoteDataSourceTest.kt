package com.example.nbateams.data.datasource

import app.cash.turbine.test
import com.example.nbateams.data.api.NBAService
import com.example.nbateams.data.datasource.playerdetail.PlayerDetailRemoteDataSourceImpl
import com.example.nbateams.stubs.stubPlayerDetailResponse
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlayerDetailRemoteDataSourceTest {

    private val service: NBAService = mock()
    private val dataSource = PlayerDetailRemoteDataSourceImpl(service)
    private val playerId = 237

    @Test
    fun `getPlayerDetail Should return PlayersDetailResponse When service returns success`() {
        runBlocking {

            // Given
            val playerDetail = stubPlayerDetailResponse
            whenever(service.getPlayerDetail(playerId)).thenAnswer { playerDetail }

            // When
            val result = dataSource.getPlayerDetail(playerId)

            // Then
            result.test {
                verify(service).getPlayerDetail(playerId)
                assertEquals(playerDetail, expectItem())
                expectComplete()
            }

        }
    }

    @Test
    fun `getPlayerDetail Should return an error When service returns internal server error`() =
        runBlocking {
            // Given
            val error = Throwable()
            whenever(service.getPlayerDetail(playerId)).thenAnswer { throw error }

            // When
            val result = dataSource.getPlayerDetail(playerId)

            // Then
            result.test {
                verify(service).getPlayerDetail(playerId)
                assertEquals(error, expectError())
                expectNoEvents()
            }
        }
}