package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.network.NetworkCharacter
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase

class RelatedCharactersPagingSlice(
    charactersRepository: CharactersEntityRepository
) : BaseListPagingSlice<NetworkCharacter, Character>(
    charactersRepository,
    EntityType.CHARACTERS,
    PagedListUseCase.RELATED_ENTITIES
)