package com.example.nbateams.data.mapper

import com.example.nbateams.data.model.response.TeamPictureResponse
import com.example.nbateams.data.model.response.TeamsListResponse
import com.example.nbateams.domain.model.TeamsList

internal class TeamsListMapper {

    fun map(response: TeamsListResponse, pictureData: List<TeamPictureResponse>) = TeamsList(
        teams = response.data.map { teamDetailResponse ->
            TeamMapper().map(teamDetailResponse, pictureData.firstOrNull {
                it.id == teamDetailResponse.id.toString()
            }?.picture ?: "")
        }
    )
}