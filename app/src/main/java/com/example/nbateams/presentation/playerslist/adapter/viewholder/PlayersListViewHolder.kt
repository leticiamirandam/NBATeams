package com.example.nbateams.presentation.playerslist.adapter.viewholder

import android.content.Context
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.databinding.PlayerItemBinding
import com.example.nbateams.domain.model.PlayersList

class PlayersListViewHolder(
    private val binding: PlayerItemBinding,
    private val context: Context,
    private val listener: (PlayersList.Player) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(player: PlayersList.Player) {
        with(binding) {
            playerName.text = "${player.firstName} ${player.lastName}"
            playerPosition.text = context.getString(R.string.position) + player.position
            binding.root.setOnClickListener {
                listener.invoke(player)
            }
        }
    }
}