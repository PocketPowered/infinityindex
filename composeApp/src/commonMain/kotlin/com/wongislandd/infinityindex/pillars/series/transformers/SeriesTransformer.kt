package com.wongislandd.infinityindex.pillars.series.transformers

import com.wongislandd.infinityindex.networking.util.ImageUrlTransformer
import com.wongislandd.infinityindex.pillars.series.models.Series
import com.wongislandd.infinityindex.pillars.series.models.NetworkSeries
import com.wongislandd.infinityindex.pillars.comics.details.transformers.RelatedLinksTransformer
import com.wongislandd.infinityindex.util.DataWrapperTransformer
import com.wongislandd.infinityindex.util.safeLet

class SeriesTransformer(
    private val imageUrlTransformer: ImageUrlTransformer,
    private val relatedLinksTransformer: RelatedLinksTransformer,
) : DataWrapperTransformer<NetworkSeries, Series>() {
    override fun itemTransformer(input: NetworkSeries): Series? {
        val relatedLinks = input.urls?.let {
            relatedLinksTransformer.transform(it)
        } ?: emptyList()
        return safeLet(
            input.id,
            input.thumbnail,
            input.name,
        ) { id, thumbnail, name ->
            Series(
                id = id,
                name = name,
                imageUrl = imageUrlTransformer.transform(thumbnail),
                description = input.description,
                modified = input.modified,
                relatedLinks = relatedLinks,
            )
        }
    }
}