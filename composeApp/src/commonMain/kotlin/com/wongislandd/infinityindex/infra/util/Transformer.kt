package com.wongislandd.infinityindex.infra.util

interface Transformer<I, O> {
    fun transform(input: I): O?
}