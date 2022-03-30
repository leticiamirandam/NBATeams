package com.example.nbateams.presentation.teamslist.adapter

import androidx.recyclerview.widget.DiffUtil
import com.example.nbateams.domain.model.TeamsList

class TeamsListDiffCallback(
    private val oldList: List<TeamsList.Team>,
    private val newList: List<TeamsList.Team>
): DiffUtil.Callback() {

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