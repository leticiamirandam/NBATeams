package com.example.nbateams.presentation.playerslist

import androidx.paging.PagingData
import com.example.nbateams.domain.model.PlayersList

data class PlayersListState(
    var isLoading: Boolean = false,
    var players: PagingData<PlayersList.Player>
)