package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.navigation.NavigationItem

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    val navController = LocalNavHostController.current
    val availableScreens = NavigationItem.entries.filter { it.idArg == null  && it != NavigationItem.Home}
    Scaffold(modifier = modifier, topBar = {
        GlobalTopAppBar()
    }) {
        Box(modifier = modifier.fillMaxSize()) {
            LazyColumn(
                modifier = Modifier.align(Alignment.Center),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(availableScreens.size) { index ->
                    val screen = availableScreens[index]
                    Button(modifier= Modifier.width(150.dp), onClick = { navController.navigate(screen.route) }) {
                        Text(screen.displayName)
                    }
                }
            }
        }
    }
}