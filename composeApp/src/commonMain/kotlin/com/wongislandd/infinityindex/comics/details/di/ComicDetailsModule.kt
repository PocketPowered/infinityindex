package com.wongislandd.infinityindex.comics.details.di

import com.wongislandd.infinityindex.comics.details.transformers.DetailedComicTransformer
import com.wongislandd.infinityindex.comics.details.transformers.DetailedComicDataWrapperTransformer
import com.wongislandd.infinityindex.comics.details.transformers.DetailedComicDataContainerTransformer
import com.wongislandd.infinityindex.comics.details.viewmodels.ComicDetailsViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val comicDetailsModule = module {
    singleOf(::DetailedComicDataWrapperTransformer)
    singleOf(::DetailedComicDataContainerTransformer)
    singleOf(::DetailedComicTransformer)
    viewModelOf(::ComicDetailsViewModel)
}