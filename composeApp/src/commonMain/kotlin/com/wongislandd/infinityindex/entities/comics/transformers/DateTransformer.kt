package com.wongislandd.infinityindex.entities.comics.transformers

import com.wongislandd.infinityindex.infra.util.Transformer
import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime

class DateTransformer: Transformer<String, String> {



    override fun transform(input: String): String {
        return formatDate(input)
    }

    private fun formatDate(input: String, timezone: String = "UTC"): String {
        // Parse the input string to an Instant (adjusting the timezone format if necessary)
        val instant = Instant.parse(input.replace(Regex("([-+][0-9]{2})([0-9]{2})$"), "$1:$2"))

        // Convert to LocalDateTime in the specified time zone
        val dateTime = instant.toLocalDateTime(TimeZone.of(timezone))

        // Format the date as "Month Day, Year"
        return "${dateTime.month.name.lowercase().replaceFirstChar { it.uppercase() }} ${dateTime.dayOfMonth}, ${dateTime.year}"
    }
}