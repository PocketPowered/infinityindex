package com.wongislandd.infinityindex.comics.list.transformers

import com.wongislandd.infinityindex.util.Transformer

class DateTransformer: Transformer<String, String> {

    //2019-10-07T00:00:00-0400 ---> 2019-10-07
    override fun transform(input: String): String {
        val dateTime = input.split("T").getOrNull(0)
        return dateTime ?: ""
    }
}