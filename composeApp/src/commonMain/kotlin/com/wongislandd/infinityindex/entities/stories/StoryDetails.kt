package com.wongislandd.infinityindex.entities.stories

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun StoryDetails(story: Story, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopLevelEntityDetails(story)
        SimpleDetailsSection(header = "Description", text = story.description)
    }
}