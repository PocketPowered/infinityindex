package com.wongislandd.infinityindex.infra.transformers

import com.wongislandd.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.infinityindex.infra.util.Transformer

/**
 * Extracts URL from network image
 */
class ImageUrlTransformer : Transformer<NetworkImage, String> {
    override fun transform(input: NetworkImage): String {
        return "${input.path}.${input.extension}".replace("http://", "https://")
    }
}