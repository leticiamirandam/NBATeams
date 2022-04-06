package com.example.nbateams.data.mapper

import com.example.nbateams.data.model.response.PlayersDetailResponse
import com.example.nbateams.domain.model.PlayersList

internal class PlayerMapper {

    fun map(playersDetailResponse: PlayersDetailResponse) = PlayersList.Player(
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