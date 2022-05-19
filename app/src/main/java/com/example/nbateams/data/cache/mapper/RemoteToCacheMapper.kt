package com.example.nbateams.data.cache.mapper

import com.example.nbateams.data.cache.model.TeamCM
import com.example.nbateams.data.model.response.TeamDetailResponse
import com.example.nbateams.data.model.response.TeamPictureResponse
import com.example.nbateams.data.model.response.TeamsListResponse
import org.jetbrains.annotations.Nullable

internal class RemoteToCacheMapper {

    fun mapTeamsListResponseToCM(
        response: TeamsListResponse,
        pictureData: List<TeamPictureResponse>
    ) =
        response.data.map { teamDetailResponse ->
            mapTeamResponseToCM(teamDetailResponse, pictureData.firstOrNull {
                it.id == teamDetailResponse.id.toString()
            }?.picture ?: "")
        }

    private fun mapTeamResponseToCM(
        teamDetailResponse: TeamDetailResponse,
        @Nullable picture: String?
    ) =
        TeamCM(
            id = teamDetailResponse.id,
            abbreviation = teamDetailResponse.abbreviation,
            city = teamDetailResponse.city,
            conference = teamDetailResponse.conference,
            division = teamDetailResponse.division,
            fullName = teamDetailResponse.fullName,
            name = teamDetailResponse.name,
            picture = picture ?: ""
        )
}