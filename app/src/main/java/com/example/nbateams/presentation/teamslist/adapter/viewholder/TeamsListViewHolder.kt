package com.example.nbateams.presentation.teamslist.adapter.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.nbateams.domain.model.TeamsList
import kotlinx.android.synthetic.main.team_item.view.*

class TeamsListViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(team: TeamsList.Team) {
        itemView.teamAbbreviation.text = team.abbreviation
        itemView.teamName.text = "Full name: " + team.fullName
        itemView.teamCity.text = "City: " + team.city
        itemView.teamDivision.text = "Division: " + team.division
        itemView.teamConference.text = "Conference: " + team.conference
    }
}