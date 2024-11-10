package com.wongislandd.infinityindex.di

import com.wongislandd.infinityindex.networking.createHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.js.Js
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<HttpClient> { createHttpClient(Js.create()) }
}