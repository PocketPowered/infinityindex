package com.wongislandd.infinityindex.comics.list.di

import com.wongislandd.infinityindex.comics.list.transformers.BasicComicTransformer
import com.wongislandd.infinityindex.comics.list.viewmodels.ComicsListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.dsl.module

val comicsListModule = module {
    single<BasicComicTransformer> { BasicComicTransformer(get(), get()) }
    viewModel { ComicsListViewModel(get()) }
}