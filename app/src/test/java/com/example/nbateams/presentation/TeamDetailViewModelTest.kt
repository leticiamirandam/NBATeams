package com.example.nbateams.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.usecase.GetTeamDetailUseCase
import com.example.nbateams.presentation.teamdetail.TeamDetailViewModel
import com.example.nbateams.stubs.stubTeamDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class TeamDetailViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    private var teamObserver: Observer<TeamsList.Team> = mock()
    private var errorObserver: Observer<Boolean> = mock()

    private var getTeamDetailUseCase: GetTeamDetailUseCase = mockk(relaxed = true)
    private lateinit var teamDetailViewModel: TeamDetailViewModel
    private val teamId: Int = 14

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        teamDetailViewModel = TeamDetailViewModel(
            teamId = teamId,
            getTeamDetailUseCase = getTeamDetailUseCase,
            dispatcher = testDispatcher
        )
        teamDetailViewModel.teamDetail.observeForever(teamObserver)
        teamDetailViewModel.isError.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTeamDetail should return TeamDetail`() {
        //Given
        reset(teamObserver)
        val teamDetail = stubTeamDetail
        every { getTeamDetailUseCase.invoke(teamId) } returns flow { emit(teamDetail) }

        //When
        teamDetailViewModel.getTeamDetail()

        //Then
        verify(teamObserver).onChanged(teamDetail)
    }

    @Test
    fun `getTeamDetail should throw error when service returns Throwable`() {
        //Given
        reset(errorObserver)
        val error = Throwable()
        every { getTeamDetailUseCase.invoke(teamId) } returns flow { throw error }

        //When
        teamDetailViewModel.getTeamDetail()

        //Then
        verify(errorObserver).onChanged(true)
    }
}