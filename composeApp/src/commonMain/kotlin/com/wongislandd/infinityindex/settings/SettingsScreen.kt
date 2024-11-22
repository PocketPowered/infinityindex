package com.wongislandd.infinityindex.settings

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Switch
import androidx.compose.material.SwitchDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.infra.util.sendEvent
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SettingsScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<SettingsViewModel>()
    val screenState by viewModel.settingsScreenStateSlice.screenState.collectAsState()
    val coroutineScope = rememberCoroutineScope()
    Scaffold(topBar = { GlobalTopAppBar("Settings") }, modifier = modifier) {
        LazyColumn(
            modifier = Modifier.fillMaxSize().padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(screenState.settings.size) { index ->
                val selectableSetting = screenState.settings[index]
                ToggleSetting(
                    selectableSetting = selectableSetting,
                    onSettingSelected = {
                        coroutineScope.sendEvent(
                            viewModel.uiEventBus,
                            SettingsUiEvent.AdjustSetting(
                                screenState.settings[index].setting,
                                selectableSetting.isSettingEnabled.not()
                            )
                        )
                    }
                )
            }
        }
    }
}

@Composable
private fun ToggleSetting(
    selectableSetting: SelectableSetting,
    onSettingSelected: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier.widthIn(max = 800.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.spacedBy(4.dp),
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = selectableSetting.setting.displayName,
                style = MaterialTheme.typography.body1,
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = selectableSetting.setting.description,
                style = MaterialTheme.typography.caption
            )
        }
        Switch(
            checked = selectableSetting.isSettingEnabled,
            onCheckedChange = { onSettingSelected() },
            colors = SwitchDefaults.colors(
                checkedThumbColor = MaterialTheme.colors.secondary
            )
        )
    }
}