package com.example.nbateams.presentation.teamslist.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.databinding.TeamItemBinding
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.presentation.teamslist.adapter.viewholder.TeamsListViewHolder

internal class TeamsListAdapter(
    private val listener: (TeamsList.Team) -> Unit
) : RecyclerView.Adapter<TeamsListViewHolder>() {

    var teamsList = emptyList<TeamsList.Team>()
        set(value) {
            val result = DiffUtil.calculateDiff(
                TeamsListDiffCallback(
                    field,
                    value
                )
            )
            result.dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TeamsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = TeamItemBinding.inflate(inflater)
        return TeamsListViewHolder(binding, parent.context, listener)
    }

    override fun onBindViewHolder(holder: TeamsListViewHolder, position: Int) {
        holder.bind(teamsList[position])
    }

    override fun getItemCount(): Int = teamsList.size
}