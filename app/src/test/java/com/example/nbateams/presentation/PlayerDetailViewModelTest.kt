package com.example.nbateams.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.nbateams.domain.usecase.GetPlayerDetailUseCase
import com.example.nbateams.presentation.playerdetail.PlayerDetailViewModel
import com.example.nbateams.stubs.stubPlayerDetail
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class PlayerDetailViewModelTest {

    @get:Rule
    val instantTaskRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    private val testDispatcher = TestCoroutineDispatcher()

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
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun `getPlayerDetail should return PlayerDetail`() {
        //Given
        val playerDetail = stubPlayerDetail
        every { getPlayerDetailUseCase.invoke(playerId) } returns flow { emit(playerDetail) }

        //When
        playerDetailViewModel.getPlayerDetail()
        val result = playerDetailViewModel.playerDetail

        //Then
        assertEquals(playerDetail, result.value)
    }
}