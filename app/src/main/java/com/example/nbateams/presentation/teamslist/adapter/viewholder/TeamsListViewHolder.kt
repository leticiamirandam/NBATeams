package com.example.nbateams.presentation.teamslist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.domain.model.TeamsList
import kotlinx.android.synthetic.main.team_item.view.*

class TeamsListViewHolder(
    itemView: View,
    private val listener: (TeamsList.Team) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(team: TeamsList.Team) {
        with(itemView) {
            teamAbbreviation.text = team.abbreviation
            teamName.text = "Full name: " + team.fullName
            teamCity.text = "City: " + team.city
            teamDivision.text = "Division: " + team.division
            teamConference.text = "Conference: " + team.conference
            setOnClickListener {
                listener.invoke(team)
            }
        }
    }
}