package br.com.connectattoo.util

import kotlinx.datetime.LocalDate
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern


@OptIn(FormatStringsInDatetimeFormats::class)
fun String.toIso8601DateFormat(): String? {
    return try {
        val formatPattern = "ddMMyyyy"

        val dateFormatter = LocalDate.Format {
            byUnicodePattern(formatPattern)
        }

        val date = dateFormatter.parse(this@toIso8601DateFormat)

        date.toString()
    } catch (e: Exception) {
        null
    }
}

