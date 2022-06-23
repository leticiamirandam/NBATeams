package com.example.nbateams.data.repository

import app.cash.turbine.test
import com.example.nbateams.data.datasource.playerslist.PlayersListRemoteDataSource
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.stubs.stubPlayersListPagingData
import com.example.nbateams.stubs.stubPlayersListPagingDataResponse
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class PlayersListRepositoryTest {

    private val playersListRemoteDataSource = mockk<PlayersListRemoteDataSource>()
    private val playersMapper = PlayerMapper()
    private val playersListRepository = PlayersListRepositoryImpl(
        playersListRemoteDataSource = playersListRemoteDataSource,
        playerMapper = playersMapper
    )

    @Test
    fun `getPlayersList Should return PlayersList When service returns`() = runBlocking {
        //Given
        val playerListResponse = stubPlayersListPagingDataResponse
        val playerList = stubPlayersListPagingData
        every { playersListRemoteDataSource.getPlayersList() } returns flowOf(playerListResponse)

        //When
        val result = playersListRepository.getPlayersList()

        //Then
        result.test {
            assertEquals(playerList, expectItem())
            expectComplete()
        }
    }
}