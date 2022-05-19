package com.example.nbateams.data.cache.mapper

import com.example.nbateams.data.cache.model.TeamCM
import com.example.nbateams.domain.model.TeamsList

internal class CacheToDomainMapper {

    fun mapTeamsListToCM(teamsListCM: List<TeamCM>) = TeamsList(
        teams = teamsListCM.map {
            mapTeamToCM(it)
        }
    )

    fun mapTeamToCM(teamCM: TeamCM) =
        TeamsList.Team(
            id = teamCM.id,
            abbreviation = teamCM.abbreviation,
            city = teamCM.city,
            conference = teamCM.conference,
            division = teamCM.division,
            fullName = teamCM.fullName,
            name = teamCM.name,
            picture = teamCM.picture
        )
}