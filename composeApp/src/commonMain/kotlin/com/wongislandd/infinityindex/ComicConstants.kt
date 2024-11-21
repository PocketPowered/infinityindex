package com.wongislandd.infinityindex

import kotlinx.datetime.Clock
import kotlin.time.Duration.Companion.days

object ComicConstants {
    const val RELATED_DETAILS_MAX_ENTITY_RESULTS = 5
    const val LIST_PAGE_SIZE = 10
    const val SPLASH_SCREEN_DURATION = 2000L
    private const val COMIC_LOOK_FORWARD_PERIOD_DAYS = 30
    val PREDEFINED_DATE_RANGE = get10YearsFromNow()

    private fun get10YearsFromNow(): String {
        val currentDate = Clock.System.now()

        // Look forward period
        val futureDate = currentDate.plus(COMIC_LOOK_FORWARD_PERIOD_DAYS.days)

        // Format the date as YYYY-MM-DD
        val startDate = "1900-01-01"
        val formattedDate = futureDate.toString()
        return "$startDate,$formattedDate"
    }
}