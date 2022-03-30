package com.example.nbateams.data.mapper

import com.example.nbateams.data.model.response.PlayersListResponse
import com.example.nbateams.domain.model.PlayersList

internal class PlayersListMapper {

    fun map(response: PlayersListResponse) = PlayersList(
        players = response.data.map { playersDetailResponse ->
            PlayersList.Player(
                id = playersDetailResponse.id,
                firstName = playersDetailResponse.firstName,
                lastName = playersDetailResponse.lastName,
                position = playersDetailResponse.position,
                heightFeet = playersDetailResponse.heightFeet.toString(),
                heightInches = playersDetailResponse.heightInches.toString(),
                weightPounds = playersDetailResponse.weightPounds.toString(),
                team = TeamMapper().map(playersDetailResponse.team)
            )
        }
    )
}