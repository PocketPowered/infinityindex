package com.wongislandd.infinityindex.comics.ui

import com.wongislandd.infinityindex.comics.models.Comic
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class ComicPreviewProvider : PreviewParameterProvider<Comic> {
    override val values: Sequence<Comic> = sequenceOf(
        Comic(
            title = "The Amazing Spiderman",
            imageUrl = "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b.jpg",
            subtitle = "2019-10-30"
        )
    )
}