package com.example.nbateams.presentation.common

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.nbateams.R

@Composable
fun TitleSubtitleContent(
    title: String,
    subtitle: String
) {
    Text(title, color = colorResource(id = R.color.text_color))
    Text(
        subtitle,
        modifier = Modifier.padding(bottom = 15.dp),
        fontSize = 24.sp,
        color = colorResource(id = R.color.text_color)
    )
}