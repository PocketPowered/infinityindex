package com.wongislandd.infinityindex.transformers.util

import com.wongislandd.infinityindex.infra.util.Transformer
import kotlinx.datetime.Instant
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTransformer: Transformer<String, String?> {

    // Updated regex to match the offset (e.g., +0000 or -0500)
    private val isoOffsetDateTimeRegex = Regex("""\d{4}-\d{2}-\d{2}T\d{2}:\d{2}:\d{2}([-+]\d{4})""")

    override fun transform(input: String): String? {
        return try {
            if (isoOffsetDateTimeRegex.matches(input)) {
                formatIsoDate(input)
            } else {
                formatSimpleDate(input)
            }
        } catch (e: Exception) {
            null
        }
    }

    private fun formatIsoDate(input: String): String {
        // Handle the timezone offset (e.g., +0000) by adding a colon for parsing
        val formattedInput = input.replace(Regex("([-+][0-9]{2})([0-9]{2})$"), "$1:$2")

        // Parse the input string to an Instant, which handles the timezone offset
        val instant = Instant.parse(formattedInput)

        // Convert to LocalDateTime in UTC (or other specified timezone if needed)
        val dateTime = instant.toLocalDateTime(TimeZone.UTC)

        // Format the date as "Month Day, Year"
        return "${dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${dateTime.dayOfMonth}, ${dateTime.year}"
    }

    private fun formatSimpleDate(input: String): String {
        val dateTime = LocalDateTime.parse(input.replace(" ", "T"))

        // Extract the date part
        val localDate = dateTime.date
        val month = localDate.month.name.lowercase().replaceFirstChar { it.uppercase() } // Capitalize the month
        val day = localDate.dayOfMonth
        val year = localDate.year
        return "$month $day, $year"
    }
}