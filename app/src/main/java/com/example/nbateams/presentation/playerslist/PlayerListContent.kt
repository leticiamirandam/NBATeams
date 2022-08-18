package com.example.nbateams.presentation.playerslist

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.paging.PagingData
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.items
import com.example.nbateams.R
import com.example.nbateams.domain.model.PlayersList
import kotlinx.coroutines.flow.Flow

@Composable
fun PlayersListContent(
    players: Flow<PagingData<PlayersList.Player>>,
    navigateToDetail: (PlayersList.Player) -> Unit
) {
    val lazyPlayerItems: LazyPagingItems<PlayersList.Player> = players.collectAsLazyPagingItems()
    Scaffold(
        backgroundColor = colorResource(id = R.color.default_background_color),
        topBar = {
            TopAppBar(
                backgroundColor = colorResource(id = R.color.orange_500),
                title = {
                    Text(text = "NBA Teams", color = colorResource(id = R.color.toolbar_text_color))
                },
                actions = {},
                elevation = 12.dp
            )
        }, content = {
            LazyColumn {
                items(lazyPlayerItems) { player ->
                    PlayerListItem(player = player!!, navigateToDetail = navigateToDetail)
                }
            }
        }
    )
}