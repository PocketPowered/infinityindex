package com.wongislandd.infinityindex.infra.di

import com.wongislandd.infinityindex.infra.networking.createHttpClient
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import org.koin.core.module.Module
import org.koin.dsl.module

actual val platformModule: Module = module {
    single<HttpClient> { createHttpClient((OkHttp.create())) }
}