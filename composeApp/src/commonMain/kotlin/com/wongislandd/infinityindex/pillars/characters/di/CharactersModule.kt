package com.wongislandd.infinityindex.pillars.characters.di

import com.wongislandd.infinityindex.pillars.characters.data.CharactersRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val charactersModule = module {
    singleOf(::CharactersRepository)
}