package com.example.meditationcomposeapp.data_source.utils

import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class DateUtils @Inject constructor() {

    fun parseStringToDate(dateValue: String, datePattern: DatePattern): LocalDate {
        val pattern = DateTimeFormatter.ofPattern(datePattern.format)
        return LocalDate.parse(dateValue, pattern)
    }

    enum class DatePattern(val format: String) {
        DATE_INCOME("MM-dd-yyyy")
    }
}