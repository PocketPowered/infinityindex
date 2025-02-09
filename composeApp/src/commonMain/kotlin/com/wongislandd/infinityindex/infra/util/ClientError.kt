package com.wongislandd.infinityindex.infra.util

enum class ClientError(override val displayMessage: String?): Error {
    TRANSFORMATION_ERROR("Error transforming data"),
    UNEXPECTED_STATE("Unexpected state"),
}