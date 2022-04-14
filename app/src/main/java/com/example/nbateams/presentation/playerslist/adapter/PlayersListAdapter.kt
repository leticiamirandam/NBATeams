package com.example.nbateams.presentation.playerslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.example.nbateams.R
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.presentation.playerslist.adapter.viewholder.PlayersListViewHolder

class PlayersListAdapter(
    private val listener: (PlayersList.Player) -> Unit
) : PagingDataAdapter<PlayersList.Player, PlayersListViewHolder>(PlayersListDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return PlayersListViewHolder(view, listener)
    }

    override fun onBindViewHolder(holder: PlayersListViewHolder, position: Int) {
        holder.bind(getItem(position)!!)
    }
}