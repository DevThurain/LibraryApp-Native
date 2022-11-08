package com.thurainx.libraryapp.utils

import android.util.Log
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*

object DateUtils {
    fun convertDateStringToMilli(dateString: String): Long{
        val formatter = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)
        return try {
            val parsedDate: Date = formatter.parse(dateString)
            parsedDate.time
        }catch (e: Exception){
            0
        }

    }
}