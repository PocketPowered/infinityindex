package com.wongislandd.infinityindex.home

import org.koin.compose.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val homeModule = module {
    factoryOf(::HomeScreenStateSlice)
    viewModelOf(::HomeViewModel)
}