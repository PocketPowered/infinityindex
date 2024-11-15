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
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.navigation.LocalNavHostController

@Composable
fun GlobalTopAppBar(
    isTitleShown: Boolean = true,
    showBackButton: Boolean = true,
    actions: (@Composable RowScope.() -> Unit) = {},
    modifier: Modifier = Modifier
) {
    val navController = LocalNavHostController.current
    val shouldShowBackButton = showBackButton && navController.previousBackStackEntry != null
    val backButton: (@Composable () -> Unit)? = if (shouldShowBackButton) {
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