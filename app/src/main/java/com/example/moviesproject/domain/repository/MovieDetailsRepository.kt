package com.example.moviesproject.domain.repository

import android.content.Context
import com.example.moviesproject.data.NetworkModule.NetworkModule
import com.example.moviesproject.data.NetworkModule.NetworkModuleResponses
import com.example.moviesproject.data.respones.*
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.GetMovieDetailsRepository
import com.example.moviesproject.hardcodedatalist.NetworkModuleProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal class MovieDetailsDataRepository(private val context: Context) : NetworkModuleProvider,
    GetMovieDetailsRepository {

    private val networkModule = NetworkModule() // needed injection in constructor todo()

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return loadDataFromApi(movieId)
    }

    private suspend fun getMovieDetailsFromApi(movieId: Int): MovieDataDetails =
        withContext(Dispatchers.IO) {
            provideNetworkModule().getMovieDetailData(movieId)
        }

    private suspend fun getImagesFromApi(): ImagesResponse = withContext(Dispatchers.IO) {
        val data = provideNetworkModule().getConfigurationData()
        val data1 = ConfigurationMovieData(images = data.images, changeKeys = data.changeKeys)
        data1.images
    }

    private suspend fun loadDataFromApi(movieId: Int): MovieDetails {
        val imagesData = getImagesFromApi()
        val data = getMovieDetailsFromApi(movieId)
        val listOfCastActor = parseActorsData(movieId, imagesData)

        return parseMovie(data, imagesData, listOfCastActor)
    }

    private suspend fun getActorsDataFromApi(movieId: Int): MovieCastsResponse =
        withContext(Dispatchers.IO)
        {
            provideNetworkModule().getCastsActorsData(id = movieId)
        }

    private suspend fun parseActorsData(movieId: Int, imagesResponse: ImagesResponse): List<CastActor> {
        val data = getActorsDataFromApi(movieId)
        return data.cast.map { castActor ->
            CastActor(
                id = castActor.id,
                name = castActor.name,
                character = castActor.character,
                imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + castActor.imageUrl
            )
        }
    }

    private fun parseMovie(
        movieDetails: MovieDataDetails,
        imagesResponse: ImagesResponse,
        listOfCastActor: List<CastActor>
    ): MovieDetails {
        return MovieDetails(
            id = movieDetails.id, //  id : Int
            title = movieDetails.title, // Main title : String
            backdropImageUrlPath = imagesResponse.baseUrl + imagesResponse.backdropSizes[3] + movieDetails.backdropImageUrlPath,
            revenue = movieDetails.revenue, //
            genres = movieDetails.genres, // List<Genre>
            voteCount = movieDetails.voteCount, // double "8.2"
            budget = movieDetails.budget, // Int
            overview = movieDetails.overview,
            runtime = movieDetails.runtime,
            posterImageUrlPath = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + movieDetails.posterImageUrlPath,
            releaseDate = movieDetails.releaseDate,
            voteAverage = (movieDetails.voteAverage/2).toInt(),
            tagline = movieDetails.tagline,
            actorList = listOfCastActor, // Warning wrong Url
            pgAge = if (movieDetails.adult) "13" else "16"
        )
    }

    override fun provideNetworkModule(): NetworkModuleResponses {
        return networkModule
    }

    private fun <T : Any> T?.orThrow(createThrowable: () -> Throwable): T {
        return this ?: throw createThrowable()
    }

}