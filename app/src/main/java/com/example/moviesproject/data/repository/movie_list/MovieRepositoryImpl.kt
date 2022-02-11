package com.example.moviesproject.data.repository.movie_list

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// context
class MovieRepositoryImpl(
    private val context: Context,
    private val parseMovie: ParseMovie,
    private val mainMoviesRepository: MainMoviesRepository,
    private val networkModule: NetworkModuleImpl
) : MovieRepository {

    private var movies: List<Movie>? = null

    init {
        Log.d("init", "MovieRepositoryImpl")
    }

    override suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val cachedMovies = movies
        if (cachedMovies != null) {
            cachedMovies
        } else {
            val moviesFromJson = loadMovieList()
            movies = moviesFromJson
            moviesFromJson
        }
    }

    private suspend fun loadMovieList(): List<Movie> = parseMovie.parse(
        mainMoviesRepository.loadMoviesApi(networkModule).results,
        mainMoviesRepository.loadGenresApi(networkModule),
        mainMoviesRepository.loadConfigurationFromApi(networkModule)
    )
}
