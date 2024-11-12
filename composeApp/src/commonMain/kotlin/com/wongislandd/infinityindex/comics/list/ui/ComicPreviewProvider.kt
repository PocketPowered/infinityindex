package com.wongislandd.infinityindex.comics.list.ui

import com.wongislandd.infinityindex.comics.list.models.BasicComic
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class ComicPreviewProvider : PreviewParameterProvider<BasicComic> {
    override val values: Sequence<BasicComic> = sequenceOf(
        BasicComic(
            id = 1,
            title = "The Amazing Spiderman",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg",
            subtitle = "2019-10-30"
        )
    )
}