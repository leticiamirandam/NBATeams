package com.example.nbateams.presentation.playerslist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.nbateams.domain.model.PlayersList

class PlayersListDiffCallback(
    private val oldList: List<PlayersList.Player>,
    private val newList: List<PlayersList.Player>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return true
    }
}