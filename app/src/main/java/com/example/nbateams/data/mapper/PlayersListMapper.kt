package com.example.nbateams.data.mapper

import com.example.nbateams.data.model.response.PlayersListResponse
import com.example.nbateams.domain.model.PlayersList

internal class PlayersListMapper {

    fun map(response: PlayersListResponse) = PlayersList(
        players = response.data.map { playersDetailResponse ->
            PlayerMapper().map(playersDetailResponse)
        }
    )
}