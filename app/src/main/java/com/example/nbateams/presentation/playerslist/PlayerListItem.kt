package com.example.nbateams.presentation.playerslist

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nbateams.R
import com.example.nbateams.domain.model.PlayersList

@Composable
fun PlayerListItem(player: PlayersList.Player, navigateToDetail: (PlayersList.Player) -> Unit) {
    Card(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp)
            .clickable { navigateToDetail(player) }
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(15.dp)),
        backgroundColor = colorResource(id = R.color.card_background_color),
        elevation = 5.dp
    ) {
        Column(
            modifier = Modifier.padding(all = 15.dp)
        ) {
            player.firstName.let {
                Text(
                    it + " " + player.lastName,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorResource(id = R.color.text_color)
                )
            }
            Text(" Position: " + player.position, color = colorResource(id = R.color.text_color))
        }
    }
}