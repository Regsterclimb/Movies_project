package com.example.moviesproject.data.repository

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.remote.NetworkModule.NetworkModuleImpl
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// context
class MovieRepositoryImpl(
    private val context: Context,
    private val parseMovie: ParseMovie,
    private val mainDataRepository: MainDataRepository,
) : MovieRepository {

    private val networkModule = NetworkModuleImpl()

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
        mainDataRepository.loadMoviesApi(networkModule).results,
        mainDataRepository.loadGenresApi(networkModule),
        mainDataRepository.loadConfigurationFromApi(networkModule)
    )

}
