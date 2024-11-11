package com.wongislandd.infinityindex.comics.di

import com.wongislandd.infinityindex.comics.data.ComicsRepository
import com.wongislandd.infinityindex.comics.transformers.ComicDataContainerTransformer
import com.wongislandd.infinityindex.comics.transformers.ComicDataWrapperTransformer
import com.wongislandd.infinityindex.comics.transformers.ComicTransformer
import com.wongislandd.infinityindex.comics.transformers.DateTransformer
import com.wongislandd.infinityindex.comics.transformers.ImageUrlTransformer
import com.wongislandd.infinityindex.comics.viewmodels.ComicsListViewModel
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