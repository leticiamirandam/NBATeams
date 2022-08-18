package com.example.nbateams.presentation.common

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.nbateams.R

@Composable
fun ToolbarContent(backButtonClick: () -> Unit) {
    TopAppBar(
        backgroundColor = colorResource(id = R.color.orange_500),
        title = {
            Text(text = "NBA Teams", color = colorResource(id = R.color.toolbar_text_color))
        },
        navigationIcon = {
            IconButton(onClick = backButtonClick) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back_button),
                    contentDescription = "back",
                    tint = colorResource(id = R.color.menu_item_color)
                )
            }
        },
        actions = {},
        elevation = 12.dp
    )
}