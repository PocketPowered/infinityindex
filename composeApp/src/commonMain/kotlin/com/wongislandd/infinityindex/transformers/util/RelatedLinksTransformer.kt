package com.wongislandd.infinityindex.transformers.util

import com.wongislandd.infinityindex.models.util.RelatedLink
import com.wongislandd.infinityindex.models.util.LinkType
import com.wongislandd.infinityindex.infra.networking.models.NetworkUrl
import com.wongislandd.infinityindex.infra.util.Transformer
import com.wongislandd.infinityindex.infra.util.safeLet

class RelatedLinksTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper,
) : Transformer<List<NetworkUrl>, List<RelatedLink>> {

    private val blacklistedLinkTypes = emptySet<LinkType>(
    )

    override fun transform(input: List<NetworkUrl>): List<RelatedLink> {
        return input.mapNotNull { networkUrl ->
            val associatedType = networkUrl.type?.let { networkFieldTypeMapper.mapLinkType(it) }
            safeLet(associatedType.takeIf { !blacklistedLinkTypes.contains(it) }, networkUrl.url) { type, url ->
                RelatedLink(type, url)
            }
        }
    }

}