package com.wongislandd.infinityindex.infra

import com.wongislandd.infinityindex.infra.viewmodels.AppLeveled
import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

object DateHelper {

    fun getCurrentYear(): Int {
        val currentDate = AppLeveled.getFreshTimestamp().toLocalDateTime(TimeZone.currentSystemDefault())
        return currentDate.year
    }

    fun isYearInTheFuture(year: Int): Boolean {
        return year > getCurrentYear()
    }
}