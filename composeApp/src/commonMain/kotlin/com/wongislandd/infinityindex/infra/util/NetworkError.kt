package com.wongislandd.infinityindex.infra.util

enum class NetworkError(override val displayMessage: String?) : Error {
    REQUEST_TIMEOUT("Request timed out"),
    UNAUTHORIZED("Unauthorized request"),
    NOT_FOUND("Resource not found"),
    CONFLICT("Network Conflict"),
    TOO_MANY_REQUESTS("Too many requests"),
    NO_INTERNET("No internet connection"),
    PAYLOAD_TOO_LARGE("Payload too large"),
    MARVEL_API_RATE_LIMITED("Marvel API Rate Limit Reached. Please try again later."),
    SERVER_ERROR("Internal Server Error"),
    SERIALIZATION("Serialization Error"),
    UNKNOWN("Unknown error");
}