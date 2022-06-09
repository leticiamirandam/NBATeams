package com.example.nbateams.stubs

import com.example.nbateams.data.model.response.PicturesListResponse
import com.example.nbateams.data.model.response.TeamPictureResponse

internal val stubPicturesListResponse =
    PicturesListResponse(
        range = "'Página1'!A2:B31",
        majorDimension = "ROWS",
        values = arrayListOf(
            arrayListOf(
                "1",
                "https://upload.wikimedia.org/wikipedia/pt/9/9a/Atlanta_Hawks.png"
            )
        )
    )

internal val stubTeamPictureResponse = listOf(
    TeamPictureResponse(
        id = "1",
        picture = "https://upload.wikimedia.org/wikipedia/pt/9/9a/Atlanta_Hawks.png"
    )
)
