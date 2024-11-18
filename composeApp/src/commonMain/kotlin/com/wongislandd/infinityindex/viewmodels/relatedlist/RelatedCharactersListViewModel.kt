package com.wongislandd.infinityindex.viewmodels.relatedlist

import com.wongislandd.infinityindex.infra.util.events.BackChannelEvent
import com.wongislandd.infinityindex.infra.util.events.EventBus
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListViewModel
import com.wongislandd.infinityindex.infra.viewmodels.SearchSlice
import com.wongislandd.infinityindex.infra.viewmodels.SortSlice
import com.wongislandd.infinityindex.models.local.Character
import com.wongislandd.infinityindex.models.network.NetworkCharacter
import com.wongislandd.infinityindex.viewmodels.shared.CharactersListScreenStateSlice

class RelatedCharactersListViewModel(
    uiEventBus: EventBus<UiEvent>,
    searchSlice: SearchSlice,
    sortSlice: SortSlice,
    relatedCharactersSlice: RelatedCharactersSlice,
    charactersListScreenStateSlice: CharactersListScreenStateSlice,
    backChannelEventBus: EventBus<BackChannelEvent>
) : BaseListViewModel<NetworkCharacter, Character>(
    charactersListScreenStateSlice,
    sortSlice,
    searchSlice,
    relatedCharactersSlice,
    uiEventBus,
    backChannelEventBus
)