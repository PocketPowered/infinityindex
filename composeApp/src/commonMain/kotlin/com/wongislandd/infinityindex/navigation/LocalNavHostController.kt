package com.wongislandd.infinityindex.navigation

import androidx.compose.runtime.compositionLocalOf
import androidx.navigation.NavHostController

val LocalNavHostController = compositionLocalOf<NavHostController> {
    error("No NavHostController provided")
}