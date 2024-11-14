package com.wongislandd.infinityindex.infra.transformers

import com.wongislandd.infinityindex.infra.models.DefaultImageType
import com.wongislandd.infinityindex.infra.models.LoadableImage
import com.wongislandd.infinityindex.infra.networking.models.NetworkImage
import com.wongislandd.infinityindex.infra.util.Transformer

data class LoadableImageTransformerInput(
    val networkImage: NetworkImage?,
    val defaultImageType: DefaultImageType
)

/**
 * Extracts URL from network image
 */
class LoadableImageTransformer : Transformer<LoadableImageTransformerInput, LoadableImage> {

    override fun transform(input: LoadableImageTransformerInput): LoadableImage {
        val imageUrl = input.networkImage?.let {
            extractImageUrl(it)
        }
        return LoadableImage(
            imageUrl = imageUrl,
            defaultEntity = input.defaultImageType
        )
    }

    private fun extractImageUrl(networkImage: NetworkImage): String {
        return "${networkImage.path}.${networkImage.extension}".replace("http://", "https://")
    }
}