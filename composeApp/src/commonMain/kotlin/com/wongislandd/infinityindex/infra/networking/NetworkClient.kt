package com.wongislandd.infinityindex.infra.networking

import com.wongislandd.infinityindex.infra.util.NetworkError
import com.wongislandd.infinityindex.infra.util.Resource
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.get
import io.ktor.client.request.url
import io.ktor.client.statement.bodyAsText
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerializationException
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class NetworkClient(val httpClient: HttpClient) {

    suspend inline fun <reified T> get(
        endpoint: String,
        serializer : KSerializer<T>,
        builder: HttpRequestBuilder.() -> Unit = {},
    ): Resource<T> {
        return makeNetworkRequest(endpoint, serializer, builder)
    }

    suspend inline fun <reified T> getFlow(
        endpoint: String,
        serializer : KSerializer<T>,
        crossinline builder: HttpRequestBuilder.() -> Unit = {}
    ): StateFlow<Resource<T>> {
        val stateFlow = MutableStateFlow<Resource<T>>(Resource.Loading)
        withContext(Dispatchers.Default) {
            makeNetworkRequestToFlow(endpoint, stateFlow, serializer, builder)
        }
        return stateFlow
    }

    suspend inline fun <reified T> makeNetworkRequest(
        endpoint: String,
        serializer : KSerializer<T>,
        builder: HttpRequestBuilder.() -> Unit = {}
    ): Resource<T> {
        try {
            val response = httpClient.get {
                url(endpoint)
                builder()
            }
            val newValue = when (response.status.value) {
                in 200..299 -> {
                    val data: T = Json.decodeFromString(serializer, response.bodyAsText())
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
            return Resource.Error(NetworkError.UNKNOWN, e)
        }
    }

    suspend inline fun <reified T> makeNetworkRequestToFlow(
        endpoint: String,
        stateFlow: MutableStateFlow<Resource<T>>,
        serializer : KSerializer<T>,
        builder: HttpRequestBuilder.() -> Unit = {}
    ) {
        stateFlow.value = makeNetworkRequest(endpoint, serializer, builder)
    }
}