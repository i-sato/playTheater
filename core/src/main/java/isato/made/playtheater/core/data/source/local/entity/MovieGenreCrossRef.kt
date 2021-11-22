package isato.made.playtheater.core.data.source.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity(primaryKeys = ["movieId", "genreId"])
data class MovieGenreCrossRef(
    val movieId: String,
    @ColumnInfo(index = true)
    val genreId: String
)
