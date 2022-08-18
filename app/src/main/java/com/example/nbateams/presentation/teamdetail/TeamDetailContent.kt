package com.example.nbateams.presentation.teamdetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.MutableLiveData
import coil.compose.AsyncImagePainter
import coil.compose.SubcomposeAsyncImage
import coil.compose.SubcomposeAsyncImageContent
import com.example.nbateams.R
import com.example.nbateams.domain.model.TeamsList
import com.example.nbateams.presentation.common.TitleSubtitleContent
import com.example.nbateams.presentation.common.ToolbarContent

@Composable
fun TeamDetailContent(
    image: String?,
    team: MutableLiveData<TeamsList.Team>,
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
                Column {
                    Column(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        SubcomposeAsyncImage(
                            model = image,
                            contentDescription = null,
                            modifier = Modifier
                                .padding(all = 15.dp)
                        ) {
                            val state = painter.state
                            if (state is AsyncImagePainter.State.Loading || state is AsyncImagePainter.State.Error) {
                                CircularProgressIndicator(
                                    modifier = Modifier.size(width = 20.dp, height = 20.dp),
                                    color = colorResource(id = R.color.orange_500)
                                )
                            } else {
                                SubcomposeAsyncImageContent(
                                    modifier = Modifier.size(
                                        width = 200.dp,
                                        height = 200.dp
                                    )
                                )
                            }
                        }
                    }
                    Column(modifier = Modifier.padding(all = 15.dp)) {
                        team.value?.let {
                            TitleSubtitleContent(title = "Full name:", subtitle = it.fullName)
                            TitleSubtitleContent(title = "City:", subtitle = it.city)
                            TitleSubtitleContent(title = "Conference:", subtitle = it.conference)
                            TitleSubtitleContent(title = "Division:", subtitle = it.division)
                            TitleSubtitleContent(
                                title = "Abbreviation:",
                                subtitle = it.abbreviation
                            )
                        }
                    }
                }
            }
        }
    )
}