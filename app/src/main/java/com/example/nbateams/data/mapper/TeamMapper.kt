package com.example.nbateams.data.mapper

import com.example.nbateams.data.model.response.TeamDetailResponse
import com.example.nbateams.domain.model.TeamsList

internal class TeamMapper {

    fun map(teamDetailResponse: TeamDetailResponse) = TeamsList.Team(
        id = teamDetailResponse.id,
        abbreviation = teamDetailResponse.abbreviation,
        city = teamDetailResponse.city,
        conference = teamDetailResponse.conference,
        division = teamDetailResponse.division,
        fullName = teamDetailResponse.fullName,
        name = teamDetailResponse.name
    )
}