package com.example.nbateams.data.mapper

import com.example.nbateams.data.model.response.TeamsListResponse
import com.example.nbateams.domain.model.TeamsList

internal class TeamsListMapper {

    fun map(response: TeamsListResponse) = TeamsList(
        teams = response.data.map { teamDetailResponse ->
            TeamMapper().map(teamDetailResponse)
        }
    )
}