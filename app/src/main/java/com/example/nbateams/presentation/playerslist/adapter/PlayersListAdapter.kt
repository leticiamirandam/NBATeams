package com.example.nbateams.presentation.playerslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.presentation.playerslist.adapter.viewholder.PlayersListViewHolder

class PlayersListAdapter() : RecyclerView.Adapter<PlayersListViewHolder>() {

    var playersList = emptyList<PlayersList.Player>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                PlayersListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlayersListViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.player_item, parent, false)
        return PlayersListViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersListViewHolder, position: Int) {
        holder.bind(playersList[position])
    }

    override fun getItemCount(): Int = playersList.size
}