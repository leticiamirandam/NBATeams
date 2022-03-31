package com.example.nbateams.presentation.playerslist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.domain.model.PlayersList
import kotlinx.android.synthetic.main.player_item.view.*

class PlayersListViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(player: PlayersList.Player) {
        itemView.playerName.text = player.firstName + " " + player.lastName
        itemView.playerPosition.text = "Position: " + player.position
        itemView.playerWeight.text = "Weight (Pounds):" + player.weightPounds
        itemView.playerHeight.text = "Height (Feet):" + player.heightFeet
    }
}