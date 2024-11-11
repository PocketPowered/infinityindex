package com.wongislandd.infinityindex.comics.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.coil3.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.wongislandd.infinityindex.comics.models.NetworkComic
import com.wongislandd.infinityindex.networking.util.getFullUrl
import com.wongislandd.infinityindex.themes.MarvelTheme
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.jetbrains.compose.ui.tooling.preview.PreviewParameter

@Composable
fun ComicCard(comic: NetworkComic, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .height(420.dp),
    ) {
        Column {
            // invalidate these, should never make it to UI if these are missing
            comic.thumbnail?.getFullUrl()?.also { url ->
                ComicImage(url, modifier = Modifier.height(300.dp))
            }
            comic.title?.also {
                ComicTitlePlate(it)
            }
        }
    }
}

@Composable
private fun ComicTitlePlate(title: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            overflow = TextOverflow.Ellipsis
        )
    }
}

@Composable
private fun ComicImage(url: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color.Black).fillMaxWidth()) {
        CoilImage(
            imageModel = { url },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            ),
            modifier = Modifier.align(Alignment.Center),
            component = rememberImageComponent {
                + ShimmerPlugin()
            },
            failure = {
                Text("Could not load image.")
            }
        )
    }
}

@Preview
@Composable
fun ComicCardPreview(@PreviewParameter(ComicPreviewProvider::class) comic: NetworkComic) {
    MarvelTheme {
        ComicCard(comic)
    }
}