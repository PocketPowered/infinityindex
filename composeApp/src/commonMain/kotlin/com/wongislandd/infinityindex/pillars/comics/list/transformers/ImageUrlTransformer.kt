package com.wongislandd.infinityindex.pillars.comics.list.transformers

import com.wongislandd.infinityindex.pillars.comics.list.models.NetworkImage
import com.wongislandd.infinityindex.util.Transformer

/**
 * Extracts URL from network image
 */
class ImageUrlTransformer : Transformer<NetworkImage, String> {
    override fun transform(input: NetworkImage): String {
        return "${input.path}.${input.extension}".replace("http://", "https://")
    }
}