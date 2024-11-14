package com.wongislandd.infinityindex.entities

import com.wongislandd.infinityindex.entities.comics.details.helpers.NetworkFieldTypeMapper
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val infraModule = module {
    singleOf(::NetworkFieldTypeMapper)
}