package com.wongislandd.infinityindex.entities.series

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.entities.comics.EntityDetails
import com.wongislandd.infinityindex.entities.comics.InformationSnippet
import com.wongislandd.infinityindex.entities.series.models.Series
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.composables.TopLevelEntityDetails
import com.wongislandd.infinityindex.infra.util.safeLet

@Composable
fun SeriesDetails(series: Series, modifier: Modifier = Modifier) {
    EntityDetails(modifier = modifier) {
        TopLevelEntityDetails(series)
        SimpleDetailsSection("Description", series.description)
        DetailsSection("Additional Information") {
            series.rating?.also { rating ->
                InformationSnippet("Rating", rating)
            }
            safeLet(series.startYear, series.endYear) { startYear, endYear ->
                InformationSnippet("Active:", "$startYear - $endYear")
            }
        }
    }
}