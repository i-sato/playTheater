package isato.made.playtheater.core.util

import android.annotation.SuppressLint
import androidx.room.TypeConverter
import java.sql.Timestamp
import java.text.SimpleDateFormat

object DateConverter {

    @SuppressLint("SimpleDateFormat")
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

}