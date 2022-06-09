package com.example.nbateams.data.repository

import androidx.paging.PagingData
import app.cash.turbine.test
import com.example.nbateams.data.datasource.PlayersListRemoteDataSource
import com.example.nbateams.data.mapper.PlayerMapper
import com.example.nbateams.stubs.stubPlayerDetail
import com.example.nbateams.stubs.stubPlayersList
import com.example.nbateams.stubs.stubPlayersListResponse
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
//        //Given
//        val playerListResponse = stubPlayersListResponse
//        val playerList = stubPlayersList
//        every { playersListRemoteDataSource.getPlayersList() } returns flowOf(
//            PagingData.from(data = stubPlayersListResponse)
//        )
//
//        //When
//        val result = playersListRepository.getPlayersList()
//
//        //Then
//        result.test {
//            assertEquals(playerList, expectItem())
//            expectComplete()
//        }
    }
}