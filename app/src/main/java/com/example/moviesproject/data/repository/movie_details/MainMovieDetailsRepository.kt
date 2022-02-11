package com.example.moviesproject.data.repository.movie_details

import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieDetailsResponse
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository

interface MainMovieDetailsRepository {

    suspend fun getMovieDetailsApi(
        movieId: Int,
        networkModuleImpl: NetworkModuleImpl
    ): MovieDetailsResponse

    suspend fun parseActorsData(
        movieId: Int,
        imagesResponse: ImagesResponse,
        networkModuleImpl: NetworkModuleImpl
    ): List<ActorResponse>

    class Base(private val mainMoviesRepository: MainMoviesRepository) :
        MainMovieDetailsRepository {

        override suspend fun getMovieDetailsApi(
            movieId: Int,
            networkModuleImpl: NetworkModuleImpl
        ): MovieDetailsResponse = networkModuleImpl.getMovieDetailData(movieId)

        override suspend fun parseActorsData(
            movieId: Int,
            imagesResponse: ImagesResponse,
            networkModuleImpl: NetworkModuleImpl
        ): List<ActorResponse> =
            mainMoviesRepository.getActorsDataFromApi(
                movieId,
                networkModuleImpl
            ).response.map { castActor ->
                ActorResponse(
                    id = castActor.id,
                    name = castActor.name,
                    character = castActor.character,
                    imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + castActor.imageUrl
                )
            }
    }
}