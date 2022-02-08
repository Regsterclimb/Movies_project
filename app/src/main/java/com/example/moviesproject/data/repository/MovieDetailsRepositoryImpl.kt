package com.example.moviesproject.data.repository

import android.content.Context
import com.example.moviesproject.data.remote.NetworkModule.NetworkModule
import com.example.moviesproject.data.remote.NetworkModule.NetworkModuleResponses
import com.example.moviesproject.data.respones.*
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsRepository
import com.example.moviesproject.hardcodedatalist.NetworkModuleProvider
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


internal class MovieDetailsDataRepositoryImpl(private val context: Context) : NetworkModuleProvider,
    MovieDetailsRepository {

    private val networkModule = NetworkModule() // needed injection in constructor todo()

    override suspend fun loadMovie(movieId: Int): MovieDetails {
        return loadDataFromApi(movieId)
    }

    private suspend fun getMovieDetailsFromApi(movieId: Int): MovieDetailsResponse =
        withContext(Dispatchers.IO) {
            provideNetworkModule().getMovieDetailData(movieId)
        }

    private suspend fun getImagesFromApi(): ImagesResponse = withContext(Dispatchers.IO) {
        val data = provideNetworkModule().getConfigurationData()
        val data1 = MovieConfigurationResponse(images = data.images, changeKeys = data.changeKeys)
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

    private suspend fun parseActorsData(
        movieId: Int,
        imagesResponse: ImagesResponse
    ): List<ActorResponse> {
        val data = getActorsDataFromApi(movieId)
        return data.response.map { castActor ->
            ActorResponse(
                id = castActor.id,
                name = castActor.name,
                character = castActor.character,
                imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + castActor.imageUrl
            )
        }
    }

    private fun parseMovie(
        movieDetailsResponse: MovieDetailsResponse,
        imagesResponse: ImagesResponse,
        listOfActorResponse: List<ActorResponse>
    ): MovieDetails {
        return MovieDetails(
            id = movieDetailsResponse.id, //  id : Int
            title = movieDetailsResponse.title, // Main title : String
            backdropImageUrlPath = imagesResponse.baseUrl + imagesResponse.backdropSizes[3] + movieDetailsResponse.backdropImageUrlPath,
            revenue = movieDetailsResponse.revenue, //
            genres = movieDetailsResponse.genres, // List<Genre>
            voteCount = movieDetailsResponse.voteCount, // double "8.2"
            budget = movieDetailsResponse.budget, // Int
            overview = movieDetailsResponse.overview,
            runtime = movieDetailsResponse.runtime,
            posterImageUrlPath = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + movieDetailsResponse.posterImageUrlPath,
            releaseDate = movieDetailsResponse.releaseDate,
            voteAverage = (movieDetailsResponse.voteAverage / 2).toInt(),
            tagline = movieDetailsResponse.tagline,
            actorResponseList = listOfActorResponse, // Warning wrong Url
            pgAge = if (movieDetailsResponse.adult) "13" else "16"
        )
    }

    override fun provideNetworkModule(): NetworkModuleResponses {
        return networkModule
    }

}