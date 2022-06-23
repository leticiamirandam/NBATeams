package com.example.nbateams.data.mapper

import com.example.nbateams.stubs.stubPlayerDetail
import com.example.nbateams.stubs.stubPlayerDetailResponse
import org.junit.Test
import kotlin.test.assertEquals

class PlayerMapperTest {

    private val mapper = PlayerMapper()

    @Test
    fun `map Should convert PlayersDetailResponse to Player`() {
        // Given
        val playerResponse = stubPlayerDetailResponse
        val player = stubPlayerDetail

        // When
        val result = mapper.map(playerResponse)

        // Then
        assertEquals(result, player)
    }
    
}