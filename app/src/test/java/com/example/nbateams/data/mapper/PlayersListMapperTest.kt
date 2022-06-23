package com.example.nbateams.data.mapper

import com.example.nbateams.stubs.stubPlayersList
import com.example.nbateams.stubs.stubPlayersListResponse
import org.junit.Test
import kotlin.test.assertEquals

class PlayersListMapperTest {

    private val mapper = PlayersListMapper()

    @Test
    fun `map Should convert PlayersListResponse to PlayerList`() {
        //Given
        val playersListResponse = stubPlayersListResponse
        val playersList = stubPlayersList

        //When
        val result = mapper.map(playersListResponse)

        //Then
        assertEquals(result, playersList)
    }
}