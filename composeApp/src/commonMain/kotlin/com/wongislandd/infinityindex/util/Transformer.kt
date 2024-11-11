package com.wongislandd.infinityindex.util

interface Transformer<I, O> {
    fun transform(input: I): O?
}