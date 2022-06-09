package com.example.nbateams.data.mapper.cache

import com.example.nbateams.data.cache.mapper.CacheToDomainMapper
import com.example.nbateams.stubs.stubTeamCM
import com.example.nbateams.stubs.stubTeamsList
import org.junit.Test
import kotlin.test.assertEquals

class CacheToDomainMapperTest {

    private val mapper = CacheToDomainMapper()

    @Test
    fun `map Should convert list of TeamCM to TeamsList`() {
        //Given
        val teamsList = stubTeamsList
        val teamsCM = listOf(stubTeamCM)

        //When
        val result = mapper.mapTeamsListToCM(teamsCM)

        //Then
        assertEquals(result, teamsList)
    }
}