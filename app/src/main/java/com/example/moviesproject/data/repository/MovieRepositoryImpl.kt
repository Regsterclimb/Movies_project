package com.example.moviesproject.data.repository

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.remote.NetworkModule.NetworkModule
import com.example.moviesproject.data.remote.NetworkModule.NetworkModuleResponses
import com.example.moviesproject.data.respones.Genre
import com.example.moviesproject.data.respones.ImagesResponse
import com.example.moviesproject.data.respones.MovieConfigurationResponse
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.model.MoviePopular
import com.example.moviesproject.domain.model.toMoviePopular
import com.example.moviesproject.domain.use_cases.MovieRepository
import com.example.moviesproject.hardcodedatalist.NetworkModuleProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

// context
class MovieRepositoryImpl(
    private val context: Context,
    private val parseMovie: ParseMovie,
    networkModule: NetworkModule
) : MovieRepository, NetworkModuleProvider {

    private val networkModule = NetworkModule()

    private var movies: List<Movie>? = null

    init {
        Log.d("init", "MovieRepositoryImpl")
    }

    override suspend fun loadMovies(): List<Movie> = withContext(Dispatchers.IO) {
        val cachedMovies = movies
        if (cachedMovies != null) {
            cachedMovies
        } else {
            val moviesFromJson = loadMoviesFromJsonFile()
            movies = moviesFromJson
            moviesFromJson
        }
    }

    private suspend fun loadMoviesFromApi(): MoviePopular = withContext(Dispatchers.IO) {
        provideNetworkModule().getMoviePopularData().toMoviePopular()
    }

    private suspend fun loadConfigurationFromApi(): ImagesResponse = withContext(Dispatchers.IO) {
        val data = provideNetworkModule().getConfigurationData()
        val data1 = MovieConfigurationResponse(images = data.images, changeKeys = data.changeKeys)
        data1.images
    }

    private suspend fun loadMoviesFromJsonFile(): List<Movie> = parseMovie.parse(
        loadMoviesFromApi().results,
        loadGenresFromApi(),
        loadConfigurationFromApi()
    )


    private suspend fun loadGenresFromApi(): List<Genre> = withContext(Dispatchers.IO) {
        val list = provideNetworkModule().getGenresData()
        list.genres.map { genresItem -> Genre(id = genresItem.id, name = genresItem.name) }

    }

    override fun provideNetworkModule(): NetworkModuleResponses {
        return networkModule
    }

}
