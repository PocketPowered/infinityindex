package com.wongislandd.infinityindex.infra.util

import com.wongislandd.infinityindex.infra.viewmodels.AppLeveled
import kotlin.time.Duration.Companion.days

object LookForwardDateHelper {
    fun getLookForwardDateRange(lookForwardDays: Int): String {
        val currentDate = AppLeveled.getFreshTimestamp()

        // Look forward period
        val futureDate = currentDate.plus(lookForwardDays.days)

        // Format the date as YYYY-MM-DD
        val startDate = "1900-01-01"
        val formattedDate = futureDate
        return "$startDate,$formattedDate"
    }
}