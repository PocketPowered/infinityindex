package com.wongislandd.infinityindex.entities.characters

import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.entities.characters.transformers.CharacterTransformer
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val charactersModule = module {
    singleOf(::CharactersEntityRepository)
    singleOf(::CharacterTransformer)
}