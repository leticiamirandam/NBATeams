package com.example.nbateams.data.mapper.cache

import com.example.nbateams.data.cache.mapper.RemoteToCacheMapper
import com.example.nbateams.data.model.response.TeamDetailResponse
import com.example.nbateams.stubs.stubTeamDetail
import com.example.nbateams.stubs.stubTeamPictureResponse
import com.example.nbateams.stubs.stubTeamsList
import com.example.nbateams.stubs.stubTeamsListResponse
import org.junit.Test
import kotlin.test.assertEquals

class RemoteToCacheMapperTest {

    private val mapper = RemoteToCacheMapper()

    @Test
    fun `map Should convert TeamsListResponse to list of TeamDetailResponse`() {
        //Given
        val teamsListResponse = stubTeamsListResponse
        val picturesList = stubTeamPictureResponse
        val teamsList = listOf(stubTeamDetail)

        //When
        val result = mapper.mapTeamsListResponseToCM(teamsListResponse, picturesList)

        //Then
        //assertEquals(result, teamsList)
    }
}