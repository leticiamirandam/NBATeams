package com.example.nbateams.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.domain.usecase.GetTeamsListUseCase
import com.example.nbateams.presentation.teamslist.TeamsListViewModel
import com.example.nbateams.stubs.stubTeamsList
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
class TeamsListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    private var teamsListObserver: Observer<TeamsList> = mock()
    private var errorObserver: Observer<Boolean> = mock()

    private var getTeamsListUseCase: GetTeamsListUseCase = mockk(relaxed = true)
    private lateinit var teamsListViewModel: TeamsListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        teamsListViewModel = TeamsListViewModel(
            getTeamsListUseCase = getTeamsListUseCase,
            dispatcher = testDispatcher
        )
        teamsListViewModel.teamsListResult.observeForever(teamsListObserver)
        teamsListViewModel.isError.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getTeamsList should return TeamsList`() {
        //Given
        reset(teamsListObserver)
        val teamsList = stubTeamsList
        every { getTeamsListUseCase.invoke() } returns flow { emit(teamsList) }

        //When
        teamsListViewModel.getTeamsList()

        //Then
        verify(teamsListObserver).onChanged(teamsList)
    }

    @Test
    fun `getTeamsList should throw error when service returns Throwable`() {
        //Given
        reset(errorObserver)
        val error = Throwable()
        every { getTeamsListUseCase.invoke() } returns flow { throw error }

        //When
        teamsListViewModel.getTeamsList()

        //Then
        verify(errorObserver).onChanged(true)
    }
}