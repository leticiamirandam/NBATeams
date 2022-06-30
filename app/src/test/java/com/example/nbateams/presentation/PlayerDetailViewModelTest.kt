package com.example.nbateams.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.domain.usecase.GetPlayerDetailUseCase
import com.example.nbateams.presentation.playerdetail.PlayerDetailViewModel
import com.example.nbateams.stubs.stubPlayerDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.verify
import org.mockito.kotlin.mock
import org.mockito.kotlin.reset

@ExperimentalCoroutinesApi
class PlayerDetailViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = UnconfinedTestDispatcher()

    private var playerObserver: Observer<PlayersList.Player> = mock()
    private var errorObserver: Observer<Boolean> = mock()

    private var getPlayerDetailUseCase: GetPlayerDetailUseCase = mockk(relaxed = true)
    private lateinit var playerDetailViewModel: PlayerDetailViewModel
    private var playerId: Int = 237

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        playerDetailViewModel = PlayerDetailViewModel(
            playerId = playerId,
            getPlayerDetailUseCase = getPlayerDetailUseCase,
            dispatcher = testDispatcher
        )
        playerDetailViewModel.playerDetail.observeForever(playerObserver)
        playerDetailViewModel.isError.observeForever(errorObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPlayerDetail should return PlayerDetail`() {
        //Given
        reset(playerObserver)
        val playerDetail = stubPlayerDetail
        every { getPlayerDetailUseCase.invoke(playerId) } returns flow { emit(playerDetail) }

        //When
        playerDetailViewModel.getPlayerDetail()

        verify(playerObserver).onChanged(playerDetail)
    }

    @Test
    fun `getPlayerDetail should throw error when service returns Throwable`() {
        //Given
        reset(errorObserver)
        val error = Throwable()
        every { getPlayerDetailUseCase.invoke(playerId) } returns flow { throw error }

        //When
        playerDetailViewModel.getPlayerDetail()

        //Then
        verify(errorObserver).onChanged(true)
    }
}