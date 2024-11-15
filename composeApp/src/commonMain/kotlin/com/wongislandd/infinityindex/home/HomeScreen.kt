package com.wongislandd.infinityindex.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.composables.GlobalTopAppBar
import com.wongislandd.infinityindex.infra.composables.ListOfEntities
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.navigation.NavigationItem
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val viewModel = koinViewModel<HomeViewModel>()
    val navController = LocalNavHostController.current
    val availableScreens = NavigationItem.entries.filter { it.idArg == null  && it != NavigationItem.Home}
    val screenState by viewModel.screenStateSlice.screenState.collectAsState()
    Scaffold(modifier = modifier, topBar = {
        GlobalTopAppBar()
    }) {
        // double check how this iteming works
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                ListOfEntities(screenState)
            }
//            items(availableScreens.size) { index ->
//                val screen = availableScreens[index]
//                Button(modifier= Modifier.width(150.dp), onClick = { navController.navigate(screen.route) }) {
//                    Text(screen.displayName)
//                }
//            }
        }
    }
}