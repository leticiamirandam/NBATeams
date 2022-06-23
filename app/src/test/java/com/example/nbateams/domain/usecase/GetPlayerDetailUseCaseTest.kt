package com.example.nbateams.domain.usecase

import app.cash.turbine.test
import com.example.nbateams.domain.repository.PlayerDetailRepository
import com.example.nbateams.stubs.stubPlayerDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetPlayerDetailUseCaseTest {

    private val repository = mockk<PlayerDetailRepository>()
    private val useCase = GetPlayerDetailUseCase(repository)
    private val playerId: Int = 1

    @Test
    fun `invoke Should return PlayerDetail When repository returns success`() = runBlocking {
        //Given
        val playerDetail = stubPlayerDetail
        every { repository.getPlayerDetail(playerId) } returns flowOf(playerDetail)

        //When
        val result = useCase.invoke(playerId)

        //Then
        result.test {
            assertEquals(playerDetail, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `invoke Should return Throwable When repository returns error`() = runBlocking {
        //Given
        val error = Throwable()
        every { repository.getPlayerDetail(playerId) } returns flow { throw error }

        //When
        val result = useCase.invoke(playerId)

        //Then
        result.test {
            assertEquals(error, expectError())
        }
    }
}