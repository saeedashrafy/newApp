package com.ashrafi.newsapp.utils

import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.util.Locale
import java.util.TimeZone

//current

// yesterday

object DateTimeUtils {

    fun formatDate(isoDate: String): String {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.getDefault())
        inputFormat.timeZone = TimeZone.getTimeZone("UTC") // Ensure input is treated as UTC

        val outputFormat = SimpleDateFormat("MMM dd, yyyy hh:mm a", Locale.getDefault())
        outputFormat.timeZone = TimeZone.getDefault() // Convert to local time zone

        val date = inputFormat.parse(isoDate) ?: return "Invalid date"
        return outputFormat.format(date)
    }

}