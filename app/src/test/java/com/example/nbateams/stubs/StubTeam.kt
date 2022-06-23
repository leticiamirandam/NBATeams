package com.example.nbateams.stubs

import com.example.nbateams.data.model.response.TeamDetailResponse
import com.example.nbateams.data.model.response.TeamsListResponse
import com.example.nbateams.domain.model.TeamsList

internal val stubTeamDetailWithPicture =
    TeamsList.Team(
        id = 14,
        abbreviation = "LAL",
        city = "Los Angeles",
        conference = "West",
        division = "Pacific",
        fullName = "Los Angeles Lakers",
        name = "Lakers",
        picture = "https://upload.wikimedia.org/wikipedia/pt/9/9a/Atlanta_Hawks.png"
    )

internal val stubTeamDetail =
    TeamsList.Team(
        id = 14,
        abbreviation = "LAL",
        city = "Los Angeles",
        conference = "West",
        division = "Pacific",
        fullName = "Los Angeles Lakers",
        name = "Lakers",
        picture = ""
    )

internal val stubTeamDetailResponse =
    TeamDetailResponse(
        id = 14,
        abbreviation = "LAL",
        city = "Los Angeles",
        conference = "West",
        division = "Pacific",
        fullName = "Los Angeles Lakers",
        name = "Lakers"
    )

internal val stubTeamsList =
    TeamsList(
        teams = listOf(stubTeamDetail)
    )

internal val stubTeamsListWithPicture =
    TeamsList(
        teams = listOf(stubTeamDetailWithPicture)
    )

internal val stubTeamsListResponse =
    TeamsListResponse(
        data = listOf(stubTeamDetailResponse)
    )