package com.example.nbateams.presentation.playerdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import com.example.nbateams.R
import com.example.nbateams.domain.model.PlayersList
import com.example.nbateams.presentation.common.TitleSubtitleContent
import com.example.nbateams.presentation.common.ToolbarContent

@Composable
fun PlayerDetailContent(
    player: MutableLiveData<PlayersList.Player>,
    backButtonClick: () -> Unit
) {
    Scaffold(
        backgroundColor = colorResource(id = R.color.default_background_color),
        topBar = {
            ToolbarContent(backButtonClick = backButtonClick)
        }, content = {
            Card(
                modifier = Modifier
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .fillMaxWidth(),
                shape = RoundedCornerShape(corner = CornerSize(15.dp)),
                backgroundColor = colorResource(id = R.color.card_background_color),
                elevation = 5.dp
            ) {
                Column(modifier = Modifier.padding(all = 15.dp)) {
                    player.value?.let {
                        TitleSubtitleContent(
                            title = "Full name:",
                            subtitle = it.firstName + " " + it.lastName
                        )
                        TitleSubtitleContent(title = "Team name:", subtitle = it.team.fullName)
                        TitleSubtitleContent(title = "Height:", subtitle = it.heightFeet + " feet")
                        TitleSubtitleContent(
                            title = "Weight:",
                            subtitle = it.weightPounds + " pounds"
                        )
                        TitleSubtitleContent(title = "Division:", subtitle = it.team.division)
                        TitleSubtitleContent(
                            title = "Abbreviation:",
                            subtitle = it.team.abbreviation
                        )
                    }
                }
            }
        }
    )
}