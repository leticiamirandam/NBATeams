package com.example.nbateams.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nbateams.domain.usecase.GetTeamDetailUseCase
import com.example.nbateams.presentation.teamdetail.TeamDetailViewModel
import com.example.nbateams.stubs.stubTeamDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class TeamDetailViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private var getTeamDetailUseCase: GetTeamDetailUseCase = mockk(relaxed = true)
    private lateinit var teamDetailViewModel: TeamDetailViewModel
    private val teamId: Int = 14

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getTeamDetail should return TeamDetail`() = runBlocking {
        //Given
        val teamDetail = stubTeamDetail
        every { getTeamDetailUseCase.invoke(teamId) } answers { flowOf(teamDetail) }

        //When
        teamDetailViewModel = TeamDetailViewModel(
            teamId = teamId,
            getTeamDetailUseCase = getTeamDetailUseCase,
            dispatcher = testDispatcher
        )
        teamDetailViewModel.getTeamDetail()

        //Then
        Assert.assertEquals(teamDetailViewModel.teamDetail, teamDetail)
    }
}