package com.example.nbateams.stubs

import com.example.nbateams.data.model.response.PlayersListResponse

internal val stubPlayersListResponse =
    PlayersListResponse(
        data = listOf(stubPlayerDetailResponse)
    )