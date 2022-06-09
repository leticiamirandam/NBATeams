package com.example.nbateams.stubs

import com.example.nbateams.domain.model.PlayersList

internal val stubPlayerDetail =
    PlayersList.Player(
        id = 237,
        firstName = "LeBron",
        lastName = "James",
        position = "F",
        heightFeet = "6",
        heightInches = "8",
        weightPounds = "250",
        team = stubTeamDetail
    )
