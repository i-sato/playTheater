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
        return value?.let { dateString ->
            val df = SimpleDateFormat("yyyy-MM-dd")
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