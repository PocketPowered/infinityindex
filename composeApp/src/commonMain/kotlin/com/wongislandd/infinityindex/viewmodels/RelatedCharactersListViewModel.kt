package com.wongislandd.infinityindex.viewmodels

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.network.NetworkCharacter
import com.wongislandd.infinityindex.viewmodels.slices.CharactersListScreenStateSlice
import com.wongislandd.infinityindex.viewmodels.slices.RelatedCharactersPagingSlice

class RelatedCharactersListViewModel(
    uiEventBus: EventBus<UiEvent>,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    relatedCharactersPagingSlice: RelatedCharactersPagingSlice,
    charactersListScreenStateSlice: CharactersListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCharacter, Character>(
    charactersListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedCharactersPagingSlice,
    uiEventBus,
    backChannelEventBus
)