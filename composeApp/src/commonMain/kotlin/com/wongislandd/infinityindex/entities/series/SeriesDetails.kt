package com.wongislandd.infinityindex.entities.series

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails

@Composable
fun SeriesDetails(series: Series, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        TopLevelEntityDetails(series)
    }
}