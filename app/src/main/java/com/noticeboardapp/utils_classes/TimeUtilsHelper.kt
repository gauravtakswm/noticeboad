package com.gauravtak.scheduler_assignment.utils_classes

import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class TimeUtilsHelper {
    companion object {
        fun getTodayDate(): String {
            val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
            val currentDate = sdf.format(Date())
            return currentDate;
        }


    }

}