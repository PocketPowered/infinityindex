package com.wongislandd.infinityindex.viewmodels.single

import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.characters.models.NetworkCharacter
import com.wongislandd.infinityindex.entities.stories.models.NetworkStory
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.repositories.StoriesEntityRepository

class SingleStorySlice(
    storyRepository: StoriesEntityRepository
): BaseSingleEntityResolutionSlice<NetworkStory, Story>(
    EntityType.STORIES,
    storyRepository
)