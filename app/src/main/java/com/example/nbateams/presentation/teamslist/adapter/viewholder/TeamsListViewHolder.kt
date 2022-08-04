package com.example.nbateams.presentation.teamslist.adapter.viewholder

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.databinding.TeamItemBinding
import com.example.nbateams.domain.model.TeamsList

class TeamsListViewHolder(
    private val binding: TeamItemBinding,
    private val context: Context,
    private val listener: (TeamsList.Team) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(team: TeamsList.Team) {
        with(binding) {
            teamAbbreviation.text = team.abbreviation
            teamName.text = "${context.getString(R.string.full_name)} ${team.fullName}"
            binding.root.setOnClickListener {
                listener.invoke(team)
            }
        }
    }
}