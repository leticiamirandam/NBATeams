package com.example.nbateams.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nbateams.domain.usecase.GetTeamsListUseCase
import com.example.nbateams.presentation.teamslist.TeamsListViewModel
import com.example.nbateams.stubs.stubTeamsList
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
class TeamsListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private var getTeamsListUseCase: GetTeamsListUseCase = mockk(relaxed = true)
    private lateinit var teamsListViewModel: TeamsListViewModel

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
    fun `getTeamsList should return TeamsList`() = runBlocking {
        //Given
        val teamsList = stubTeamsList
        every { getTeamsListUseCase.invoke() } answers { flowOf(teamsList) }

        //When
        teamsListViewModel = TeamsListViewModel(
            getTeamsListUseCase = getTeamsListUseCase,
            dispatcher = testDispatcher
        )
        teamsListViewModel.getTeamsList()

        //Then
        Assert.assertEquals(teamsListViewModel.teamsListResult, teamsList)
    }
}