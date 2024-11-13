package com.wongislandd.infinityindex.networking.util

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.SerializationException

abstract class NetworkClient(val httpClient: HttpClient) {

    suspend inline fun <reified T> get(
        endpoint: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): Resource<T> {
        return makeNetworkRequest(endpoint, builder)
    }

    suspend inline fun <reified T> getFlow(
        endpoint: String,
        crossinline builder: HttpRequestBuilder.() -> Unit = {}
    ): StateFlow<Resource<T>> {
        val stateFlow = MutableStateFlow<Resource<T>>(Resource.Loading)
        withContext(Dispatchers.Default) {
            makeNetworkRequestToFlow(endpoint, stateFlow, builder)
        }
        return stateFlow
    }

    suspend inline fun <reified T> makeNetworkRequest(
        endpoint: String,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): Resource<T> {
        try {
            val response = httpClient.get {
                url(endpoint)
                builder()
            }
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
            return newValue
        } catch (e: UnresolvedAddressException) {
            return Resource.Error(NetworkError.NO_INTERNET)
        } catch (e: SerializationException) {
            return Resource.Error(NetworkError.SERIALIZATION)
        } catch (e: Exception) {
            return Resource.Error(NetworkError.UNKNOWN)
        }
    }

    suspend inline fun <reified T> makeNetworkRequestToFlow(
        endpoint: String,
        stateFlow: MutableStateFlow<Resource<T>>,
        builder: HttpRequestBuilder.() -> Unit = {}
    ) {
        stateFlow.value = makeNetworkRequest(endpoint, builder)
    }
}