package com.wongislandd.infinityindex.entities.comics.list.di

import com.wongislandd.infinityindex.entities.comics.list.viewmodels.ComicsListViewModel
import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val comicsListModule = module {
    viewModelOf(::ComicsListViewModel)
}