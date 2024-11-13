package com.wongislandd.infinityindex.entities.series.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.entities.series.models.NetworkSeries
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.util.safeLet

class SeriesTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
) : DataWrapperTransformer<NetworkSeries, Series>() {
    override fun itemTransformer(input: NetworkSeries): Series? {
        return safeLet(
            input.id,
            input.thumbnail,
            input.title,
        ) { id, thumbnail, title ->
            Series(
                id = id,
                displayName = title,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                description = input.description,
                rating = input.rating,
                startYear = input.startYear,
                endYear = input.endYear,
            )
        }
    }
}