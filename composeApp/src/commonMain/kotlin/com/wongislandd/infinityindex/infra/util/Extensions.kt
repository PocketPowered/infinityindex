package com.wongislandd.infinityindex.infra.util

import androidx.compose.ui.Modifier

fun Modifier.conditionallyChain(condition: Boolean, modifier: Modifier): Modifier {
    return if (condition) this.then(modifier) else this
}