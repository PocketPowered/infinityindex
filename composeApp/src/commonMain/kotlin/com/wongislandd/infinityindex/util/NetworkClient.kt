package com.wongislandd.infinityindex.util

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException

abstract class NetworkClient(val httpClient: HttpClient) {

    suspend inline fun <reified T> get(
        endpoint: String,
    ): StateFlow<Resource<T>> {
        val stateFlow = MutableStateFlow<Resource<T>>(Resource.Loading)
        withContext(Dispatchers.Default) {
            makeNetworkRequest(endpoint, stateFlow)
        }
        return stateFlow

    }

    suspend inline fun <reified T> makeNetworkRequest(
        endpoint: String,
        stateFlow: MutableStateFlow<Resource<T>>
    ) {
        try {
            val response = httpClient.get(endpoint)
            val newValue = when (response.status.value) {
                in 200..299 -> {
                    val data: T = response.body()
                    Resource.Success(data)
                }
                401 -> Resource.Error(NetworkError.UNAUTHORIZED)
                404 -> Resource.Error(NetworkError.NOT_FOUND)
                409 -> Resource.Error(NetworkError.CONFLICT)
                408 -> Resource.Error(NetworkError.REQUEST_TIMEOUT)
                413 -> Resource.Error(NetworkError.PAYLOAD_TOO_LARGE)
                in 500..599 -> Resource.Error(NetworkError.SERVER_ERROR)
                else -> Resource.Error(NetworkError.UNKNOWN)
            }
            stateFlow.value = newValue
        } catch (e: UnresolvedAddressException) {
            stateFlow.value = Resource.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            stateFlow.value = Resource.Error(NetworkError.SERIALIZATION)
        }
    }
}