package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.Comic
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.Event
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.DisplayableEntity

@Composable
fun TopLevelEntityDetails(displayableEntity: DisplayableEntity, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(horizontal = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MarvelImage(image = displayableEntity.image, modifier = Modifier.fillMaxWidth())
        Text(
            text = displayableEntity.displayName,
            modifier = Modifier.padding(16.dp),
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center
        )
        EntityTypePlate(displayableEntity)
    }
}

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
            .background(MaterialTheme.colors.primary) // Background color of the pill
            .padding(horizontal = 16.dp, vertical = 8.dp) // Padding for the text
    ) {
        Text(
            text = text,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.caption
        )
    }
}