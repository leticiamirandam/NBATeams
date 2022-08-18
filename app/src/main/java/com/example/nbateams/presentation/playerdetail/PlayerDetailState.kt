package com.example.nbateams.presentation.playerdetail

import androidx.lifecycle.MutableLiveData
import com.example.nbateams.domain.model.PlayersList

data class PlayerDetailState(
    var isLoading: Boolean = false,
    var player: MutableLiveData<PlayersList.Player> = MutableLiveData()
)