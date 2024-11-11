package com.wongislandd.infinityindex.di

import com.wongislandd.infinityindex.comics.data.ComicsRepository
import com.wongislandd.infinityindex.comics.viewmodels.ComicsListViewModel
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val appModule = module {
    single<ComicsRepository> { ComicsRepository(get()) }
    viewModel { ComicsListViewModel(get()) }
}

fun initializeKoin() {
    startKoin {
        modules(appModule, platformModule)
    }
}