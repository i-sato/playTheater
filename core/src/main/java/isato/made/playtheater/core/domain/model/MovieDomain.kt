package isato.made.playtheater.core.domain.model

data class MovieDomain(
    val movieId: String,
    val title: String,
    val overview: String? = null,
    val posterPath: String? = null
)
