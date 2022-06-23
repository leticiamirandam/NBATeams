package com.example.nbateams.domain.usecase

import app.cash.turbine.test
import com.example.nbateams.domain.repository.TeamsListRepository
import com.example.nbateams.stubs.stubTeamsList
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetTeamsListUseCaseTest {

    private val repository = mockk<TeamsListRepository>()
    private val useCase = GetTeamsListUseCase(repository)

    @Test
    fun `invoke Should return TeamsList When repository returns success`() = runBlocking {
        //Given
        val teamsList = stubTeamsList
        every { repository.getTeamsList() } returns flowOf(teamsList)

        //When
        val result = useCase.invoke()

        //Then
        result.test {
            assertEquals(teamsList, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `invoke Should return Throwable When repository returns error`() = runBlocking {
        //Given
        val error = Throwable()
        every { repository.getTeamsList() } returns flow { throw error }

        //When
        val result = useCase.invoke()

        //Then
        result.test {
            assertEquals(error, expectError())
        }
    }
}