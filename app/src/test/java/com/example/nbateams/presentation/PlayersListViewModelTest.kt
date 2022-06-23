package com.example.nbateams.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import com.example.nbateams.presentation.playerslist.PlayersListViewModel
import com.example.nbateams.stubs.stubPlayersListPagingData
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
class PlayersListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

    private var getPlayersListUseCase: GetPlayersListUseCase = mockk(relaxed = true)
    private lateinit var playersListViewModel: PlayersListViewModel

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
    fun `getPlayersList should return PlayersList`() = runBlocking {
        //Given
        val playersList = stubPlayersListPagingData
        every { getPlayersListUseCase.invoke() } answers { flowOf(playersList) }

        //When
        playersListViewModel = PlayersListViewModel(
            playersListUseCase = getPlayersListUseCase,
            dispatcher = testDispatcher
        )
        playersListViewModel.getPlayersList()

        //Then
        Assert.assertEquals(playersListViewModel.playersList, playersList)
    }
}