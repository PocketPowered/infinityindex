package com.wongislandd.infinityindex.di

import com.wongislandd.infinityindex.comics.list.di.comicsModule
import org.koin.core.context.startKoin
import org.koin.core.module.Module
import org.koin.dsl.module

expect val platformModule: Module

val appModule = module {

}

fun initializeKoin() {
    startKoin {
        modules(appModule, platformModule, comicsModule)
    }
}