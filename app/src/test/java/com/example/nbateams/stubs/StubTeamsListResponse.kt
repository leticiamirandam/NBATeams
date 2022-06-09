package com.example.nbateams.stubs

import com.example.nbateams.data.model.response.TeamsListResponse

internal val stubTeamsListResponse =
    TeamsListResponse(
        data = listOf(stubTeamDetailResponse)
    )