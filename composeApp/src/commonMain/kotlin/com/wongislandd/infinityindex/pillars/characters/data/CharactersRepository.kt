package com.wongislandd.infinityindex.pillars.characters.data

import com.wongislandd.infinityindex.networking.util.NetworkClient
import io.ktor.client.HttpClient

class CharactersRepository(
    okHttpClient: HttpClient
): NetworkClient(okHttpClient) {
}