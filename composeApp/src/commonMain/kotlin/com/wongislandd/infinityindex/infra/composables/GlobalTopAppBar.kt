package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import kotlinx.coroutines.flow.map

@Composable
fun GlobalTopAppBar(
    isTitleShown: Boolean = true,
    showBackButton: Boolean = true,
    actions: (@Composable RowScope.() -> Unit) = {},
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    val previousBackStackEntry by navController.currentBackStackEntryFlow
        .map { navController.previousBackStackEntry }
        .collectAsState(initial = null)
    val canNavigateBack = previousBackStackEntry != null && showBackButton

    val backButton: (@Composable () -> Unit)? = if (canNavigateBack) {
        {
            IconButton(onClick = { navController.popBackStack() }) {
                Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
            }
        }
    } else {
        null
    }
    TopAppBar(
        title = {
            if (isTitleShown) {
                Text("Infinity Index", color = MaterialTheme.colors.onPrimary)
            }
        },
        navigationIcon = backButton,
        modifier = modifier,
        actions = actions,
        backgroundColor = MaterialTheme.colors.primary
    )
}