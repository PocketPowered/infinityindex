package com.wongislandd.infinityindex.entities.stories

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection

@Composable
fun StoryDetails(story: Story, modifier: Modifier = Modifier) {
    EntityDetails(story, modifier) {
        SimpleDetailsSection(header = "Description", text = story.description)
    }
}