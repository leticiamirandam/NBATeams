package com.example.nbateams.presentation.playerslist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.domain.model.PlayersList
import kotlinx.android.synthetic.main.player_item.view.*

class PlayersListViewHolder(
    itemView: View,
    private val listener: (PlayersList.Player) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(player: PlayersList.Player) {
        with(itemView) {
            playerName.text = "${player.firstName} ${player.lastName}"
            playerPosition.text = context.getString(R.string.position) + player.position
            setOnClickListener {
                listener.invoke(player)
            }
        }
    }
}