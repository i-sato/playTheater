package isato.made.playtheater.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "genre")
data class GenreEntity(

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "genreId")
    val genreId: String,

    @ColumnInfo(name = "name")
    val name: String

)
