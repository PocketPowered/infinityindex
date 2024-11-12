package com.wongislandd.infinityindex.comics.details.transformers

import com.wongislandd.infinityindex.comics.details.models.RelatedLink
import com.wongislandd.infinityindex.comics.details.viewmodels.NetworkFieldTypeMapper
import com.wongislandd.infinityindex.comics.list.models.NetworkUrl
import com.wongislandd.infinityindex.util.Transformer
import com.wongislandd.infinityindex.util.safeLet

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