package com.wongislandd.infinityindex.pillars.comics.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
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
import com.wongislandd.infinityindex.navigation.LocalNavHostController
import com.wongislandd.infinityindex.navigation.RouteHelper
import com.wongislandd.infinityindex.pillars.comics.details.models.Comic

@Composable
fun ComicCard(comic: Comic, modifier: Modifier = Modifier) {
    val navController = LocalNavHostController.current
    Card(
        modifier = modifier
            .background(MaterialTheme.colors.surface)
            .fillMaxWidth()
            .height(460.dp)
            .clickable {
                navController.navigate(
                    RouteHelper.getComicDetailsRouteForId(
                        comic.id
                    )
                )
            },
        elevation = 8.dp
    ) {
        Column {
            ComicListImage(
                url = comic.imageUrl,
                modifier = Modifier.height(300.dp)
            )
            ComicTitlePlate(comic.title)
        }
    }
}

@Composable
private fun ComicTitlePlate(
    title: String,
    subtitle: String? = null,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.h5,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSurface,
            overflow = TextOverflow.Ellipsis
        )
        subtitle?.also {
            Text(
                text = it,
                style = MaterialTheme.typography.h6,
                color = MaterialTheme.colors.onSurface,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Composable
private fun ComicListImage(url: String, modifier: Modifier = Modifier) {
    Box(modifier = modifier.background(Color.Black).fillMaxWidth()) {
        CoilImage(
            imageModel = { url },
            imageOptions = ImageOptions(
                contentScale = ContentScale.Inside,
                alignment = Alignment.Center
            ),
            modifier = Modifier.align(Alignment.Center),
            component = rememberImageComponent {
                +ShimmerPlugin()
            },
            failure = {
                Text("Could not load image.")
            }
        )
    }
}