package com.wongislandd.infinityindex.pillars.comics.list.di

import com.wongislandd.infinityindex.pillars.comics.list.transformers.BasicComicTransformer
import com.wongislandd.infinityindex.pillars.comics.list.viewmodels.ComicsListViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicsListModule = module {
    singleOf(::BasicComicTransformer)
    viewModelOf(::ComicsListViewModel)
}