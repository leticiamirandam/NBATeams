package com.example.nbateams.stubs

import androidx.paging.PagingData
import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.data.model.response.PlayersListResponse
import com.example.nbateams.domain.model.PlayersList
import org.mockito.kotlin.stub

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

internal val stubPlayersList =
    PlayersList(
        players = listOf(stubPlayerDetail)
    )

internal val stubPlayersListResponse =
    PlayersListResponse(
        data = listOf(stubPlayerDetailResponse)
    )

internal val stubPlayersListPagingData = PagingData.from(
    listOf(stubPlayerDetail)
)

internal val stubPlayersPagingData = PagingData.from(
    arrayListOf(stubPlayerDetail)
)


internal val stubPlayersListPagingDataResponse = PagingData.from(
    listOf(stubPlayerDetailResponse)
)