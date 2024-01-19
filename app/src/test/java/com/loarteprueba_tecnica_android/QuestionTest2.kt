import com.loarteprueba_tecnica_android.Movie
import com.loarteprueba_tecnica_android.Platform
import org.junit.Test
import org.junit.jupiter.api.Assertions

class QuestionTest2 {
    val moviesList = listOf(
        Movie(movieId = "Movie1", title = "The movie 1"),
        Movie(movieId = "Movie2", title = "The movie 2"),
        Movie(movieId = "Movie3", title = "The movie 3"),
        Movie(movieId = "Movie4", title = "The movie 4")
    )
    val platformsList = listOf(
        Platform(movieId = "Movie1", inService1 = false, inService2 = false),
        Platform(movieId = "Movie2", inService1 = true, inService2 = false),
        Platform(movieId = "Movie4", inService1 = true, inService2 = true)
    )

    data class MovieInPlatform(val movieId: String, val title: String, val inService2: Boolean)

    fun getMoviesInPlatforms(
        movies: List<Movie>,
        platforms: List<Platform>
    ): List<MovieInPlatform> {
        val movieInPlatforms = mutableListOf<MovieInPlatform>()

        for (movie in movies) {
            val platform = platforms.find { it.movieId == movie.movieId }
            if (platform != null && (platform.inService1 || platform.inService2)) {
                val isInService2 = platform.inService2
                movieInPlatforms.add(MovieInPlatform(movie.movieId, movie.title, isInService2))
            }
        }

        return movieInPlatforms
    }

    @Test
    fun `test get movies in platforms result`() {
        val result = getMoviesInPlatforms(moviesList, platformsList)
        println(result)
        // Assert on how many movies are from the service 2
        Assertions.assertEquals(2, result.count { it.inService2 })
    }

    /* Falla porque se esperaba que hubiera 2 películas en el servicio 2, pero en realidad solo hay 1
       He añadido un par de test de comprobación
    */


    @Test
    fun `test get movies in platforms result fix`() {
        val result = getMoviesInPlatforms(moviesList, platformsList)

        // Assert on how many movies are from the service 2
        Assertions.assertEquals(1, result.count { it.inService2 })
    }

    @Test
    fun `test print movies in servers`() {
        val result = getMoviesInPlatforms(moviesList, platformsList)

        val moviesInService1 = result.count { !it.inService2 }
        println("Movies in Service 1: $moviesInService1")
        result.filter { !it.inService2 }.forEach {
            println("Movie ID: ${it.movieId}, Title: ${it.title}")
        }

        val moviesInService2 = result.count { it.inService2 }
        println("\nMovies in Service 2: $moviesInService2")
        result.filter { it.inService2 }.forEach {
            println("Movie ID: ${it.movieId}, Title: ${it.title}")
        }
    }

}

