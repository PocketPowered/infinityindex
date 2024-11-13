package com.wongislandd.infinityindex.pillars.stories.di

import com.wongislandd.infinityindex.pillars.stories.data.StoriesRepository
import com.wongislandd.infinityindex.pillars.stories.transformers.StoryTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storiesModule = module {
    singleOf(::StoriesRepository)
    singleOf(::StoryTransformer)
}