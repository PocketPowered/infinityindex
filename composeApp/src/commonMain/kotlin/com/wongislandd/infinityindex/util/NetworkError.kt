package com.wongislandd.infinityindex.util

enum class NetworkError : Error {
    REQUEST_TIMEOUT,
    UNAUTHORIZED,
    NOT_FOUND,
    CONFLICT,
    TOO_MANY_REQUESTS,
    NO_INTERNET,
    PAYLOAD_TOO_LARGE,
    SERVER_ERROR,
    SERIALIZATION,
    UNKNOWN;
}