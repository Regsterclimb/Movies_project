package com.example.moviesproject.domain.repository

import android.content.Context
import android.util.Log
import com.example.moviesproject.data.NetworkModule.NetworkModule
import com.example.moviesproject.data.NetworkModule.NetworkModuleResponses
import com.example.moviesproject.data.remote.dto.ConfigurationMovieData
import com.example.moviesproject.data.remote.dto.Genre
import com.example.moviesproject.data.remote.dto.ImagesData
import com.example.moviesproject.data.remote.dto.ResultsMovie
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.model.MoviePopular
import com.example.moviesproject.domain.use_cases.GetMovieRepository
import com.example.moviesproject.hardcodedatalist.NetworkModuleProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class MovieRepository(private val context: Context) : GetMovieRepository,NetworkModuleProvider {

    private val networkModule = NetworkModule()

    private var movies: List<Movie>? = null

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
        val data1 = provideNetworkModule().getMoviePopularData()
        MoviePopular(
            page = data1.page,
            totalPages = data1.totalPages,
            results = data1.results,
            totalResults = data1.totalResults
        )
    }

    private suspend fun loadConfigurationFromApi() : ImagesData = withContext(Dispatchers.IO) {
        val data = provideNetworkModule().getConfigurationData()
        val data1 = ConfigurationMovieData( images = data.images, changeKeys = data.changeKeys)
        data1.images
    }

    private suspend fun loadMoviesFromJsonFile(): List<Movie> {
        val genresMap = loadGenresFromApi()
        val imagesData = loadConfigurationFromApi()

        Log.d("list2withgenres", "$genresMap")

        val data = loadMoviesFromApi().results
        return parseMovies(data, genresMap, imagesData)
    }

    private suspend fun loadGenresFromApi(): List<Genre> = withContext(Dispatchers.IO) {
        val list = provideNetworkModule().getGenresData()
        list.genres.map { genresItem -> Genre(id = genresItem.id, name = genresItem.name) }

    }

    private fun parseMovies(
        dataListResultMovie: List<ResultsMovie>,
        genreData: List<Genre>,
        imagesData: ImagesData
    ): List<Movie> {
        val genresMap = genreData.associateBy(Genre::id)

        return dataListResultMovie.map { jsonMovie ->
            Log.d("jsonMovie", "${jsonMovie.genreIds}")
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                storyLine = jsonMovie.storyLine,
                imageUrl = imagesData.baseUrl + imagesData.posterSizes[4] +  jsonMovie.posterPathUrl,
                detailImageUrl = jsonMovie.backdropPathUrl,
                rating = (jsonMovie.ratings / 2).toInt(),
                reviewCount = jsonMovie.voteCount,
                pgAge = if (jsonMovie.adult) 16 else 13,
                releaseDate = jsonMovie.releaseDate,
                genres = jsonMovie.genreIds.map { id ->
                    genresMap[id].orThrow { IllegalArgumentException("Genre not found") }
                },
                isLiked = false
            )
        }
    }

    override fun provideNetworkModule(): NetworkModuleResponses {
        return networkModule
    }

    private fun <T : Any> T?.orThrow(createThrowable: () -> Throwable): T {
        return this ?: throw createThrowable()
    }

}
