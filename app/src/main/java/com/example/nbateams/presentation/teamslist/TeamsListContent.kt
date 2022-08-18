package com.example.nbateams.presentation.teamslist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nbateams.R
import com.example.nbateams.domain.model.TeamsList

@Composable
fun TeamsListContent(
    teams: MutableList<TeamsList.Team>,
    navigateToDetail: (TeamsList.Team) -> Unit,
    changeTheme: () -> Unit
) {
    Scaffold(
        backgroundColor = colorResource(id = R.color.default_background_color),
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.orange_500),
                title = {
                    Text(text = "NBA Teams", color = colorResource(id = R.color.toolbar_text_color))
                },
                actions = {
                    IconButton(onClick = changeTheme) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_theme),
                            "change_theme_icon",
                            tint = colorResource(id = R.color.menu_item_color)
                        )
                    }
                },
                elevation = 12.dp
            )
        }, content = {
            LazyColumn {
                items(
                    items = teams,
                    itemContent = { TeamListItem(team = it, navigateToDetail = navigateToDetail) }
                )
            }
        }
    )
}
