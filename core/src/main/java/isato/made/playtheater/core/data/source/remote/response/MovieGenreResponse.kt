package isato.made.playtheater.core.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class MovieGenreResponse(

    @field:SerializedName("name")
    val name: String,

    @field:SerializedName("id")
    val id: String
)
