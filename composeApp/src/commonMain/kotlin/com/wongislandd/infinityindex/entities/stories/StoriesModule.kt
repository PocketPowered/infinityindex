package com.wongislandd.infinityindex.entities.stories

import com.wongislandd.infinityindex.repositories.StoriesEntityRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val storiesModule = module {
    singleOf(::StoriesEntityRepository)
    singleOf(::StoryTransformer)
}