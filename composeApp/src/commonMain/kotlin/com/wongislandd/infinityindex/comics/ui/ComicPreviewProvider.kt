package com.wongislandd.infinityindex.comics.ui

import com.wongislandd.infinityindex.comics.models.NetworkComic
import com.wongislandd.infinityindex.comics.models.NetworkImage
import org.jetbrains.compose.ui.tooling.preview.PreviewParameterProvider

class ComicPreviewProvider: PreviewParameterProvider<NetworkComic> {
    override val values: Sequence<NetworkComic> = sequenceOf(
        NetworkComic(title = "The Amazing Spiderman",
            thumbnail = NetworkImage(
                path = "http://i.annihil.us/u/prod/marvel/i/mg/3/50/526548a343e4b",
                extension = "jpg"
            )
        )
    )
}