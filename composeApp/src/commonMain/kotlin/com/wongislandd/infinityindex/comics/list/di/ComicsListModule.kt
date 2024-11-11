package com.wongislandd.infinityindex.comics.list.di

import com.wongislandd.infinityindex.comics.list.data.ComicsRepository
import com.wongislandd.infinityindex.comics.list.transformers.ComicDataContainerTransformer
import com.wongislandd.infinityindex.comics.list.transformers.ComicDataWrapperTransformer
import com.wongislandd.infinityindex.comics.list.transformers.ComicTransformer
import com.wongislandd.infinityindex.comics.list.transformers.DateTransformer
import com.wongislandd.infinityindex.comics.list.transformers.ImageUrlTransformer
import com.wongislandd.infinityindex.comics.list.viewmodels.ComicsListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val comicsModule = module {
    single<ImageUrlTransformer> { ImageUrlTransformer() }
    single<DateTransformer> { DateTransformer() }
    single<ComicTransformer> { ComicTransformer(get(), get()) }
    single<ComicDataWrapperTransformer> { ComicDataWrapperTransformer(get()) }
    single<ComicDataContainerTransformer> { ComicDataContainerTransformer(get()) }
    single<ComicsRepository> { ComicsRepository(get(), get()) }
    viewModel { ComicsListViewModel(get()) }
}