package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.PagingData
import com.wongislandd.infinityindex.entities.characters.models.Character
import com.wongislandd.infinityindex.entities.comics.details.models.ComicDetailsScreenState
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.entities.events.models.ComicEvent
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.entities.stories.models.Story
import com.wongislandd.infinityindex.util.ViewModelSlice
import com.wongislandd.infinityindex.util.events.BackChannelEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ComicDetailsScreenStateSlice : ViewModelSlice() {

    // TODO See if we can directly depend on the field in the other slice, would need
    // some guarantee of the same instance that's registered to the VM. Look into scope
    private val _characterPagingData: MutableStateFlow<PagingData<Character>> =
        MutableStateFlow(PagingData.empty())

    private val _creatorsPagingData: MutableStateFlow<PagingData<Creator>> =
        MutableStateFlow(PagingData.empty())

    private val _seriesPagingData: MutableStateFlow<PagingData<Series>> =
        MutableStateFlow(PagingData.empty())

    private val _eventsPagingData: MutableStateFlow<PagingData<ComicEvent>> =
        MutableStateFlow(PagingData.empty())

    private val _storiesPagingData: MutableStateFlow<PagingData<Story>> =
        MutableStateFlow(PagingData.empty())

    private val _screenState: MutableStateFlow<ComicDetailsScreenState> =
        MutableStateFlow(ComicDetailsScreenState(
            characterData = _characterPagingData,
            creatorsData = _creatorsPagingData,
            seriesData = _seriesPagingData,
            eventsData = _eventsPagingData,
            storiesData = _storiesPagingData
        ))

    val screenState: StateFlow<ComicDetailsScreenState> = _screenState

    override fun handleBackChannelEvent(event: BackChannelEvent) {
        when (event) {
            is ComicDetailsBackChannelEvent.ComicsResUpdate -> {
                _screenState.value = _screenState.value.copy(comicRes = event.update)
            }

            is ComicDetailsBackChannelEvent.CharacterResUpdate -> {
                _characterPagingData.value = event.update
            }
            is ComicDetailsBackChannelEvent.CreatorResUpdate -> {
                _creatorsPagingData.value = event.update
            }
        }
    }
}