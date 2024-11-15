package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.models.NavigationContext
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.util.conditionallyChain

@Composable
fun EntityCard(
    image: LoadableImage,
    title: String,
    navContext: NavigationContext,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    val totalHeight = 275.dp
    val width = 200.dp
    Card(
        modifier = modifier
            .wrapContentHeight()
            .conditionallyChain(navContext.allowNavigation, Modifier.clickable {
                navController.navigate(navContext.navRoute)
            }),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(totalHeight)
        ) {
            MarvelImage(
                image = image,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(width)
            )
            Box(
                modifier = Modifier
                    .fillMaxHeight()
                    .widthIn(max = width)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.body1,
                    color = MaterialTheme.colors.onPrimary,
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.Center)
                        .padding(16.dp),
                    textAlign = TextAlign.Center,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 2
                )
            }
        }
    }
}