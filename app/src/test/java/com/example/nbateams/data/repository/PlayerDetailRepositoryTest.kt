package com.example.nbateams.data.repository

import app.cash.turbine.test
import com.example.nbateams.data.datasource.PlayerDetailRemoteDataSource
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.stubs.stubPlayerDetail
import com.example.nbateams.stubs.stubPlayerDetailResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlayerDetailRepositoryTest {

    private val playerDetailRemoteDataSource = mockk<PlayerDetailRemoteDataSource>()
    private val playerMapper = PlayerMapper()
    private val playerId: Int = 237
    private val playerDetailRepository = PlayerDetailRepositoryImpl(
        playerDetailRemoteDataSource = playerDetailRemoteDataSource,
        playerDetailMapper = playerMapper
    )

    @Test
    fun `getPlayerDetail Should return Player When service returns`() = runBlocking {
        //Given
        val playerDetailResponse = stubPlayerDetailResponse
        val playerDetail = stubPlayerDetail
        every { playerDetailRemoteDataSource.getPlayerDetail(playerId) } returns flowOf(
            playerDetailResponse
        )

        //When
        val result = playerDetailRepository.getPlayerDetail(playerId)

        //Then
        result.test {
            assertEquals(playerDetail, expectItem())
            expectComplete()
        }
    }
}