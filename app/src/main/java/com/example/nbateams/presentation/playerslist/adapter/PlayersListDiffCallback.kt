package com.example.nbateams.presentation.playerslist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.nbateams.domain.model.PlayersList

class PlayersListDiffCallback() : DiffUtil.ItemCallback<PlayersList.Player>() {

    override fun areItemsTheSame(
        oldItem: PlayersList.Player,
        newItem: PlayersList.Player
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: PlayersList.Player,
        newItem: PlayersList.Player
    ): Boolean {
        return oldItem == newItem
    }
}