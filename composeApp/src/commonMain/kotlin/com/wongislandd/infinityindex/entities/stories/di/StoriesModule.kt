package com.wongislandd.infinityindex.entities.stories.di

import com.wongislandd.infinityindex.entities.stories.data.StoriesEntityRepository
import com.wongislandd.infinityindex.entities.stories.transformers.StoryTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storiesModule = module {
    singleOf(::StoriesEntityRepository)
    singleOf(::StoryTransformer)
}