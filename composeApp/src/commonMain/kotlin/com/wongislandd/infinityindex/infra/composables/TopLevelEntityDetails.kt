package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.util.DisplayableEntity
import com.wongislandd.infinityindex.infra.util.EntityModel
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.models.local.Creator
import com.wongislandd.infinityindex.models.local.Event
import com.wongislandd.infinityindex.models.local.Series
import com.wongislandd.infinityindex.models.local.Story

@Composable
fun TopLevelEntityDetails(entity: EntityModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(8.dp)) {
            MarvelImage(
                image = entity.image,
                tint = MaterialTheme.colors.onSurface,
                modifier = Modifier.fillMaxSize()
            )
        }
        Text(
            text = entity.displayName,
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        EntityTypePlate(entity)
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Composable
fun BottomLevelEntityDetails(entity: EntityModel, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier,
    ) {
        DetailsSection("Developer Details") {
            Text("ID: ${entity.id}", style = MaterialTheme.typography.body2)
        }
    }
}

//@Composable
//private fun EntityMetadata(entity: EntityModel, modifier: Modifier = Modifier) {
//    if (entity is Comic) {
//        Column(modifier = modifier, verticalArrangement = Arrangement.spacedBy(4.dp)) {
//            Text(
//                text = "Published: ${entity.lastModified}",
//                style = MaterialTheme.typography.body2,
//                color = MaterialTheme.colors.onSurface,
//                modifier = modifier
//            )
//        }
//    }
//}


@Composable
private fun EntityTypePlate(displayableEntity: DisplayableEntity, modifier: Modifier = Modifier) {
    val text = when (displayableEntity) {
        is Character -> "Character"
        is Event -> "Event"
        is Story -> "Story"
        is Comic -> "Comic"
        is Series -> "Series"
        is Creator -> "Creator"
        else -> "Unknown"
    }
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .clip(RoundedCornerShape(50)) // Makes the edges rounded
            .background(MaterialTheme.colors.secondary) // Background color of the pill
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding for the text
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onSecondary,
            style = MaterialTheme.typography.caption
        )
    }
}