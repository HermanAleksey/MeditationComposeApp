package com.example.meditationcomposeapp.presentation.utils

import java.text.SimpleDateFormat
import java.util.*

enum class DateFormat(val dateFormat: String) {
    DD_MM_YYYY("dd/MM/yyyy")
}

fun formatMillisIntoDate(
    locale: Locale = Locale.ENGLISH,
    milliSeconds: Long,
    dateFormat: DateFormat,
): String {
    // Create a DateFormatter object for displaying date in specified format.
    val formatter = SimpleDateFormat(dateFormat.dateFormat, locale)

    // Create a calendar object that will convert the date and time value in milliseconds to date.
    val calendar: Calendar = Calendar.getInstance()
    calendar.timeInMillis = milliSeconds
    return formatter.format(calendar.time)
}