package com.example.nbateams.domain.usecase

import app.cash.turbine.test
import com.example.nbateams.domain.repository.TeamDetailRepository
import com.example.nbateams.stubs.stubTeamDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.time.ExperimentalTime

@ExperimentalTime
class GetTeamDetailUseCaseTest {

    private val repository = mockk<TeamDetailRepository>()
    private val useCase = GetTeamDetailUseCase(repository)
    private val teamId: Int = 12

    @Test
    fun `invoke Should return TeamDetail When repository returns success`() = runBlocking {
        //Given
        val teamDetail = stubTeamDetail
        every { repository.getTeamDetail(teamId) } returns flowOf(teamDetail)

        //When
        val result = useCase.invoke(teamId)

        //Then
        result.test {
            assertEquals(teamDetail, expectItem())
            expectComplete()
        }
    }

    @Test
    fun `invoke Should return Throwable When repository returns error`() = runBlocking {
        //Given
        val error = Throwable()
        every { repository.getTeamDetail(teamId) } returns flow { throw error }

        //When
        val result = useCase.invoke(teamId)

        //Then
        result.test {
            assertEquals(error, expectError())
        }
    }
}