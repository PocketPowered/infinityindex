package com.wongislandd.infinityindex.entities.series

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.wongislandd.infinityindex.infra.DateHelper
import com.wongislandd.infinityindex.infra.EntityDetails
import com.wongislandd.infinityindex.infra.composables.DetailsSection
import com.wongislandd.infinityindex.infra.composables.InformationSnippet
import com.wongislandd.infinityindex.infra.composables.RelatedCreatorsSection
import com.wongislandd.infinityindex.infra.composables.SimpleDetailsSection
import com.wongislandd.infinityindex.infra.util.safeLet
import com.wongislandd.infinityindex.models.local.Series

@Composable
fun SeriesDetails(series: Series, modifier: Modifier = Modifier) {
    EntityDetails(series, modifier = modifier) {
        SimpleDetailsSection("Description", series.description)
        DetailsSection("Additional Information") {
            series.rating?.also { rating ->
                InformationSnippet("Rating", rating)
            }
            safeLet(series.startYear, series.endYear) { startYear, endYear ->
                val endYearOrPresent =
                    if (DateHelper.isYearInTheFuture(endYear)) "Present" else endYear
                InformationSnippet("Active", "$startYear - $endYearOrPresent")
            }
        }
        RelatedCreatorsSection(series.creatorsByRole, emptyMap())
    }
}