package com.wongislandd.infinityindex.entities.comics.details.transformers

import com.wongislandd.infinityindex.entities.comics.details.models.RelatedLink
import com.wongislandd.infinityindex.entities.comics.details.helpers.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.infra.networking.models.NetworkUrl
import com.wongislandd.infinityindex.infra.util.Transformer
import com.wongislandd.infinityindex.infra.util.safeLet

class RelatedLinksTransformer(
    private val networkFieldTypeMapper: NetworkFieldTypeMapper,
) : Transformer<List<NetworkUrl>, List<RelatedLink>> {

    override fun transform(input: List<NetworkUrl>): List<RelatedLink> {
        return input.mapNotNull { networkUrl ->
            val associatedType = networkUrl.type?.let { networkFieldTypeMapper.mapLinkType(it) }
            safeLet(associatedType, networkUrl.url) { type, url ->
                RelatedLink(type, url)
            }
        }
    }

}