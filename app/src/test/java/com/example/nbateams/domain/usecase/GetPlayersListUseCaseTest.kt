package com.example.nbateams.domain.usecase

import app.cash.turbine.test
import com.example.nbateams.domain.repository.PlayersListRepository
import com.example.nbateams.stubs.stubPlayersListPagingData
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetPlayersListUseCaseTest {

    private val repository = mockk<PlayersListRepository>()
    private val useCase = GetPlayersListUseCase(repository)

    @Test
    fun `invoke Should return PlayersList When repository returns success`() = runBlocking {
        //Given
        val playersList = stubPlayersListPagingData
        every { repository.getPlayersList() } returns flowOf(playersList)

        //When
        val result = useCase.invoke()

        //Then
        result.test {
            assertEquals(playersList, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `invoke Should return Throwable When repository returns error`() = runBlocking {
        //Given
        val error = Throwable()
        every { repository.getPlayersList() } returns flow { throw error }

        //When
        val result = useCase.invoke()

        //Then
        result.test {
            assertEquals(error, expectError())
        }
    }
}