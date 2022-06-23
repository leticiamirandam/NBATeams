package com.example.nbateams.data.mapper.cache

import com.example.nbateams.data.cache.mapper.CacheToDomainMapper
import com.example.nbateams.stubs.stubTeamListCM
import com.example.nbateams.stubs.stubTeamsListWithPicture
import org.junit.Test
import kotlin.test.assertEquals

class CacheToDomainMapperTest {

    private val mapper = CacheToDomainMapper()

    @Test
    fun `map Should convert list of TeamCM to TeamsList`() {
        //Given
        val teamsList = stubTeamsListWithPicture
        val teamsCM = stubTeamListCM

        //When
        val result = mapper.mapTeamsListToCM(teamsCM)

        //Then
        assertEquals(result, teamsList)
    }
}