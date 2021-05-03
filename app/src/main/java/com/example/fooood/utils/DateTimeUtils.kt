package com.example.fooood.utils

import java.security.spec.EllipticCurve
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit

object DateTimeUtils {
    const val DASHED_PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss"
    const val ONE_MINUTE = 1

    fun getCurrentDateTime(format: String = DASHED_PATTERN_YYYY_MM_DD_HH_MM_SS): String {
        val simpleDateFormat = SimpleDateFormat(format, Locale.ENGLISH)
        return simpleDateFormat.format(Date())
    }

    private fun differenceInTime(oldDateTime: String, currentDateTime: String): Long {
        var toReturn: Long = 0L

        val simpleDateFormat = SimpleDateFormat(DASHED_PATTERN_YYYY_MM_DD_HH_MM_SS, Locale.ENGLISH)
        val oldDate = simpleDateFormat.parse(oldDateTime)
        val currentDate = simpleDateFormat.parse(currentDateTime)

        if (oldDate != null && currentDate != null) {
            toReturn = currentDate.time - oldDate.time
        }

        return toReturn
    }

    fun differenceInMinutes(oldDateTime: String, currentDateTime: String) = TimeUnit.MILLISECONDS.toMinutes(DateTimeUtils.differenceInTime(oldDateTime, currentDateTime))
}