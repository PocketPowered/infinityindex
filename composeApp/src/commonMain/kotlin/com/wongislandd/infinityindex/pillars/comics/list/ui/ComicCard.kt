package com.wongislandd.infinityindex.pillars.comics.list.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.composables.MarvelImage
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
            MarvelImage(
                imageUrl = comic.imageUrl,
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