package com.wongislandd.infinityindex.viewmodels.slices

import com.wongislandd.infinityindex.infra.ListUiEvent
import com.wongislandd.infinityindex.infra.PagingBackChannelEvent
import com.wongislandd.infinityindex.infra.util.EntityType
import com.wongislandd.infinityindex.infra.util.events.UiEvent
import com.wongislandd.infinityindex.infra.viewmodels.BaseListScreenStateSlice
import com.wongislandd.infinityindex.infra.viewmodels.ComicListScreenState
import com.wongislandd.infinityindex.models.local.Comic
import com.wongislandd.infinityindex.repositories.CachingPreferenceRepository
import com.wongislandd.infinityindex.settings.ToggleSetting
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ComicsListScreenStateSlice(
    private val cachingPreferenceRepository: CachingPreferenceRepository
) : BaseListScreenStateSlice<Comic>(
    EntityType.COMICS,
) {
    private val _screenState: MutableStateFlow<ComicListScreenState> =
        MutableStateFlow(
            ComicListScreenState(
                isDigitallyAvailableFilterEnabled = cachingPreferenceRepository.getCachedBooleanPreference(
                    ToggleSetting.DIGITALLY_AVAILABLE
                ),
                isVariantsEnabled = cachingPreferenceRepository.getCachedBooleanPreference(
                    ToggleSetting.VARIANTS
                ),
                listState = listState.value
            )
        )

    val screenState: StateFlow<ComicListScreenState> = _screenState

    override fun afterInit() {
        super.afterInit()
        sliceScope.launch {
            listState.onEach { newListState ->
                _screenState.update {
                    it.copy(listState = newListState)
                }
            }
        }
    }

    override fun handleUiEvent(event: UiEvent) {
        super.handleUiEvent(event)
        when (event) {
            is ListUiEvent.ToggleDigitalAvailabilityFilter -> {
                _screenState.update {
                    it.copy(
                        isDigitallyAvailableFilterEnabled = event.selected
                    )
                }
                sliceScope.launch {
                    backChannelEvents.sendEvent(
                        PagingBackChannelEvent.SubmitDigitalAvailabilityFilterChange(
                            event.selected
                        )
                    )
                    cachingPreferenceRepository.saveBooleanPreference(
                        ToggleSetting.DIGITALLY_AVAILABLE,
                        event.selected
                    )
                }
            }

            is ListUiEvent.ToggleVariantsFilter -> {
                _screenState.update {
                    it.copy(
                        isVariantsEnabled = event.selected
                    )
                }
                sliceScope.launch {
                    backChannelEvents.sendEvent(
                        PagingBackChannelEvent.VariantsFilterChange(
                            event.selected
                        )
                    )
                    cachingPreferenceRepository.saveBooleanPreference(
                        ToggleSetting.VARIANTS,
                        event.selected
                    )
                }
            }
        }
    }


}