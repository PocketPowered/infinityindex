package com.wongislandd.infinityindex.entities.comics.details.viewmodels

import androidx.paging.PagingData
import androidx.paging.cachedIn
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import com.wongislandd.infinityindex.infra.paging.EntityPagingSource
import com.wongislandd.infinityindex.entities.comics.details.ui.ComicDetailsUiEvent
import com.wongislandd.infinityindex.entities.creators.data.CreatorsEntityRepository
import com.wongislandd.infinityindex.entities.creators.models.Creator
import com.wongislandd.infinityindex.infra.paging.RelatedEntityPagingSource
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.ViewModelSlice
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class ComicDetailsCreatorsSlice(
    private val creatorsRepository: CreatorsEntityRepository
) : ViewModelSlice() {

    private val _creatorsPagingData: MutableStateFlow<PagingData<Creator>> =
        MutableStateFlow(PagingData.empty())
    val creatorsPagingData: StateFlow<PagingData<Creator>> = _creatorsPagingData

    override fun handleUiEvent(event: UiEvent) {
        when (event) {
            is ComicDetailsUiEvent.PageInitialized -> {
                initialize(event.comicId)
            }
        }
    }

    private fun initialize(comicId: Int) {
        load(comicId)
    }

    private fun load(comicId: Int) {
        sliceScope.launch {
            Pager(
                config = PagingConfig(
                    initialLoadSize = 5,
                    pageSize = 5,
                    enablePlaceholders = false,
                    prefetchDistance = 5
                )
            ) {
                RelatedEntityPagingSource(creatorsRepository, EntityType.COMICS, comicId)
            }.flow.cachedIn(sliceScope).collectLatest {
                _creatorsPagingData.value = it
                backChannelEvents.sendEvent(
                    ComicDetailsBackChannelEvent.CreatorResUpdate(it)
                )
            }
        }
    }

}