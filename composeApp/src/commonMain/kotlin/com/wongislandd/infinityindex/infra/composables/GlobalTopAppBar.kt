package com.wongislandd.infinityindex.infra.composables

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController
import com.wongislandd.infinityindex.infra.navigation.NavigationHelper
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun GlobalTopAppBar(
    title: String? = null,
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
            Icon(Icons.AutoMirrored.Filled.ArrowBack,
                contentDescription = "Back",
                modifier = Modifier.combinedClickable(
                    onClick = { navController.popBackStack() },
                    onLongClick = { navController.navigate(
                        NavigationHelper.getBrowseRoute()
                    ) {
                        popUpTo(NavigationHelper.getBrowseRoute()) { inclusive = true }
                    } }
                ).padding(16.dp))
        }
    } else {
        null
    }
    TopAppBar(
        title = {
            if (isTitleShown) {
                Text(
                    title ?: "Infinity Index", color = MaterialTheme.colors.onPrimary,
                    maxLines = 1, overflow = TextOverflow.Ellipsis
                )
            }
        },
        navigationIcon = backButton,
        modifier = modifier,
        actions = actions,
        backgroundColor = MaterialTheme.colors.primary
    )
}