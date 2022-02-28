package com.m2lifeApps.movieDb.core.common

import java.text.SimpleDateFormat
import java.util.*

/**
 * @user: murat.balci
 */

object DateHelper {

    /**
     * Transform Date String
     * dd.MM.yyyy -> yyyy-MM-dd
     */
    fun formatDate(date: String): String {
        val birthDate = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()).parse(date)
        val calendar = Calendar.getInstance().apply { time = birthDate!! }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        return "$year-$month-$day"
    }

    /**
     * Transform Date String
     * dd.MM.yyyy -> yyyy-MM-dd
     */
    fun getMonthandYear(date: String): Pair<Int, Int> {
        val birthDate = SimpleDateFormat("dd/M/yyyy hh:mm:ss", Locale.getDefault()).parse(date)
        val calendar = Calendar.getInstance().apply { time = birthDate!! }
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH) + 1
        return Pair(year, month)
    }

}
