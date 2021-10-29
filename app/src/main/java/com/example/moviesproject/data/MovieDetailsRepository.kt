package com.example.moviesproject.data

import android.content.Context
import com.example.moviesproject.data.NetworkModule.NetworkModule
import com.example.moviesproject.data.NetworkModule.NetworkModuleGetData
import com.example.moviesproject.data.actors.CastActor
import com.example.moviesproject.data.actors.MovieCastsData
import com.example.moviesproject.data.configurationdata.ConfigurationMovieData
import com.example.moviesproject.data.configurationdata.ImagesData
import com.example.moviesproject.data.genresdata.Genre
import com.example.moviesproject.data.moviedata.MovieDataDetails
import com.example.moviesproject.data.moviedata.MovieDetails
import com.example.moviesproject.hardcodedatalist.NetworkModuleProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

interface MovieDetailsRepository {

    suspend fun loadMovie(movieId: Int): MovieDetails
}

internal class MovieDetailsDataRepository(private val context: Context) : NetworkModuleProvider,
    MovieDetailsRepository {

    private val networkModule = NetworkModule()


    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return loadDataFromApi(movieId)
    }


    private suspend fun getGenresFromApi(): List<Genre> = withContext(Dispatchers.IO) {
        val list = provideNetworkModule().getGenresData()
        list.genres.map { genresItem -> Genre(id = genresItem.id, name = genresItem.name) }

    }

    private suspend fun getMovieDetailsFromApi(movieId: Int): MovieDataDetails =
        withContext(Dispatchers.IO) {
            provideNetworkModule().getMovieDetailData(movieId)
        }

    private suspend fun getImagesFromApi(): ImagesData = withContext(Dispatchers.IO) {
        val data = provideNetworkModule().getConfigurationData()
        val data1 = ConfigurationMovieData(images = data.images, changeKeys = data.changeKeys)
        data1.images
    }

    private suspend fun loadDataFromApi(movieId: Int): MovieDetails {
        val genresMap = getGenresFromApi()
        val imagesData = getImagesFromApi()
        val data = getMovieDetailsFromApi(movieId)
        val listOfCastActor = parseActorsData(movieId,imagesData)


        return parseMovie(data, genresMap, imagesData, listOfCastActor)
    }

    private suspend fun getActorsDataFromApi(movieId: Int): MovieCastsData = withContext(Dispatchers.IO)
    {
        provideNetworkModule().getCastsActorsData(id = movieId)
    }

    private suspend fun parseActorsData(movieId: Int,imagesData: ImagesData): List<CastActor> {
        val data = getActorsDataFromApi(movieId)
        return data.cast.map { castActor ->
            CastActor(
                id = castActor.id,
                name = castActor.name,
                character = castActor.character,
                imageUrl = imagesData.baseUrl + imagesData.posterSizes[4] + castActor.imageUrl
            )
        }
    }

    private fun parseMovie(
        movieDetails: MovieDataDetails,
        genreData: List<Genre>,
        imagesData: ImagesData,
        listOfCastActor: List<CastActor>
    ): MovieDetails {
        val genresMap = genreData.associateBy(Genre::id)
        return MovieDetails(
            id = movieDetails.id, //  id : Int
            title = movieDetails.title, // Main title : String
            backdropImageUrlPath = imagesData.baseUrl + imagesData.backdropSizes[3] + movieDetails.backdropImageUrlPath,
            revenue = movieDetails.revenue,
            genres = movieDetails.genres, // List<Genre>
            voteCount = movieDetails.voteCount, // double "8.2"
            budget = movieDetails.budget, // Int
            overview = movieDetails.overview,
            runtime = movieDetails.runtime,
            posterImageUrlPath = imagesData.baseUrl + imagesData.posterSizes[4] + movieDetails.posterImageUrlPath,
            releaseDate = movieDetails.releaseDate,
            voteAverage = movieDetails.voteAverage,
            tagline = movieDetails.tagline,
            adult = movieDetails.adult,
            status = movieDetails.status,
            actorList = listOfCastActor, // Warning wrong Url
            pgAge = if (movieDetails.adult) "13" else "16"
        )
    }

    override fun provideNetworkModule(): NetworkModuleGetData {
        return networkModule
    }

    private fun <T : Any> T?.orThrow(createThrowable: () -> Throwable): T {
        return this ?: throw createThrowable()
    }

}