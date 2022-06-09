package com.example.nbateams.stubs

import com.example.nbateams.data.model.response.PlayersDetailResponse

internal val stubPlayerDetailResponse =
    PlayersDetailResponse(
        id = 237,
        firstName = "LeBron",
        lastName = "James",
        position = "F",
        heightFeet = 6,
        heightInches = 8,
        weightPounds = 250,
        team = stubTeamDetailResponse
    )