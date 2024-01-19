fun getMoviesInPlatforms(
    movies: List<Movie>,
    platforms: List<Platform>
): List<MovieInPlatform> {
    val platformMap = platforms.associateBy { it.movieId }

    return movies
        .mapNotNull { movie ->
            val platform = platformMap[movie.movieId]

            if (platform != null && (platform.inService1 || platform.inService2)) {
                val platformName = when {
                    platform.inService2 -> "Service2"
                    platform.inService1 -> "Service1"
                    else -> "Unknown Platform"
                }
                MovieInPlatform(movie.movieId, platformName)
            } else {
                null
            }
        }
}
