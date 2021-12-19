/*
 * PlayTheater.core
 * DateConverter.kt
 * Created by Isato on 19/12/2021 8:43:11
 * Source code available on: https://github.com/i-sato/PlayTheater
 * Copyright (c) 2021. All rights reserved.
 */

package isato.made.playtheater.core.util

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.sql.Timestamp
import java.text.SimpleDateFormat

@SuppressLint("SimpleDateFormat")
object DateConverter {

    @TypeConverter
    @JvmStatic
    fun stringToTimestamp(value: String?): Long? {
        return stringToTimestamp(value, "yyyy-MM-dd")
    }

    fun stringToTimestamp(value: String?, pattern: String = "yyyy-MM-dd"): Long? {
        return value?.let { dateString ->
            val df = SimpleDateFormat(pattern)
            val date = df.parse(dateString)
            date?.let {
                val ts = Timestamp(it.time)
                ts.time
            }
        }
    }

    @TypeConverter
    @JvmStatic
    fun fromTimestamp(value: Long?): String? {
        return value?.let {
            SimpleDateFormat("dd MMM yyyy").format(it)
        }
    }

}