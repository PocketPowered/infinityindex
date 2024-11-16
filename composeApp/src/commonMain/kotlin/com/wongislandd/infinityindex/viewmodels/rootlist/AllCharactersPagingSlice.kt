package com.wongislandd.infinityindex.viewmodels.rootlist

import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.network.NetworkCharacter
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.viewmodels.BaseListPagingSlice
import com.wongislandd.infinityindex.infra.viewmodels.PagedListUseCase
import com.wongislandd.infinityindex.repositories.CharactersEntityRepository

class AllCharactersPagingSlice(
    repository: CharactersEntityRepository
): BaseListPagingSlice<NetworkCharacter, Character>(
    repository,
    EntityType.CHARACTERS,
    PagedListUseCase.ALL_AVAILABLE
)