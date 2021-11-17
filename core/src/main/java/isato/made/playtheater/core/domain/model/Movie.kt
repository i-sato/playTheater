package isato.made.playtheater.core.domain.model

data class Movie(
    val movieId: String,
    val title: String,
    val overview: String? = null,
    val posterPath: String? = null
)
