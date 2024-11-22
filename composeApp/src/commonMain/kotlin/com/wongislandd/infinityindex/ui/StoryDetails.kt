package com.wongislandd.infinityindex.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.infra.composables.RelatedCreatorsSection
import com.wongislandd.infinityindex.models.local.Story
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection

@Composable
fun StoryDetails(story: Story, modifier: Modifier = Modifier) {
    EntityDetails(story, modifier) {
        SimpleDetailsSection(header = "Description", text = story.description)
        RelatedCreatorsSection(story.creatorsByRole, emptyMap())
    }
}