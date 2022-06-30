package com.example.nbateams.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagingData
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.usecase.GetPlayersListUseCase
import com.example.nbateams.presentation.playerslist.PlayersListViewModel
import com.example.nbateams.stubs.stubPlayersListPagingData
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
import org.mockito.Mockito
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset
import org.mockito.kotlin.verify

@ExperimentalCoroutinesApi
class PlayersListViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    private var playersListObserver: Observer<PagingData<PlayersList.Player>> = mock()
    private var errorObserver: Observer<Boolean> = mock()

    private var getPlayersListUseCase: GetPlayersListUseCase = mockk(relaxed = true)
    private lateinit var playersListViewModel: PlayersListViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        playersListViewModel = PlayersListViewModel(
            playersListUseCase = getPlayersListUseCase,
            dispatcher = testDispatcher
        )
        playersListViewModel.playersList.observeForever(playersListObserver)
        playersListViewModel.isError.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPlayersList should return PlayersList`() {
        //Given
        reset(playersListObserver)
        val playersList = stubPlayersListPagingData
        every { getPlayersListUseCase.invoke() } returns flow { emit(playersList) }

        //When
        playersListViewModel.getPlayersList()

        //Then
        verify(playersListObserver).onChanged(playersList)
    }

    @Test
    fun `getPlayersList should throw error when service returns Throwable`() {
        //Given
        reset(errorObserver)
        val error = Throwable()
        every { getPlayersListUseCase.invoke() } returns flow { throw error }

        //When
        playersListViewModel.getPlayersList()

        //Then
        Mockito.verify(errorObserver).onChanged(true)
    }
}