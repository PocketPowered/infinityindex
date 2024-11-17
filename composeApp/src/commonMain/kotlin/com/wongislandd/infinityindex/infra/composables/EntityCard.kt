package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.infra.util.conditionallyChain

private val entityCardHeight = 275.dp
private val entityCardWidth = 200.dp

@Composable
fun EntityCard(
    entity: EntityModel,
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    BaseEntityCard(
        modifier = modifier
            .conditionallyChain(entity.navContext.allowNavigation, Modifier.clickable {
                navController.navigate(entity.navContext.navRoute)
            })
    ) {
        MarvelImage(
            image = entity.image,
            contentScale = ContentScale.Crop,
            tint = MaterialTheme.colors.onPrimary,
            modifier = Modifier.size(
                entityCardWidth
            )
        )
        Box(
            modifier = Modifier
                .fillMaxHeight()
        ) {
            Text(
                text = entity.displayName,
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

@Composable
fun GhostEntityCard(modifier: Modifier = Modifier) {
    BaseEntityCard(modifier = modifier) { }
}

@Composable
fun SeeMoreEntityCard(modifier: Modifier = Modifier) {
    BaseEntityCard(modifier = modifier) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "See all",
                style = MaterialTheme.typography.body1,
                color = MaterialTheme.colors.onPrimary,
                modifier = Modifier.padding(16.dp),
                textAlign = TextAlign.Center
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                contentDescription = "See More",
                tint = MaterialTheme.colors.onPrimary
            )
        }
    }
}

@Composable
private fun BaseEntityCard(
    modifier: Modifier = Modifier,
    content: @Composable ColumnScope.() -> Unit
) {
    Card(
        modifier = modifier
            .wrapContentHeight(),
        backgroundColor = MaterialTheme.colors.primary,
        shape = RoundedCornerShape(16.dp)
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.height(entityCardHeight).width(entityCardWidth)
        ) {
            content()
        }
    }
}