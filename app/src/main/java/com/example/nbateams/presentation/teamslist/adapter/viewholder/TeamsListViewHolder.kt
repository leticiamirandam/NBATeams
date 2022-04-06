package com.example.nbateams.presentation.teamslist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.R
import com.example.nbateams.domain.model.TeamsList
import kotlinx.android.synthetic.main.team_item.view.*

class TeamsListViewHolder(
    itemView: View,
    private val listener: (TeamsList.Team) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(team: TeamsList.Team) {
        with(itemView) {
            teamAbbreviation.text = team.abbreviation
            teamName.text = "${context.getString(R.string.full_name)} ${team.fullName}"
            setOnClickListener {
                listener.invoke(team)
            }
        }
    }
}