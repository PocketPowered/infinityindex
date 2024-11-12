package com.wongislandd.infinityindex.comics.details.di

import com.wongislandd.infinityindex.comics.details.transformers.DetailedComicTransformer
import org.koin.dsl.module

val comicDetailsModule = module {
    single { DetailedComicTransformer(get(), get()) }
}