package com.example.moviesproject.data.storage.repository.movie_details

import com.example.moviesproject.data.remote.network_module.NetworkModule
import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieDetailsResponse
import com.example.moviesproject.data.storage.repository.movie_list.MainDataMoviesRepository

interface MainMovieDetailsDataRepository {

    suspend fun getMovieDetailsApi(movieId: Int): MovieDetailsResponse

    suspend fun parseActorsData(movieId: Int, imagesResponse: ImagesResponse): List<ActorResponse>

    class Base(
        private val mainDataMoviesRepository: MainDataMoviesRepository,
        private val networkModule: NetworkModule
    ) : MainMovieDetailsDataRepository {

        override suspend fun getMovieDetailsApi(
            movieId: Int
        ): MovieDetailsResponse = networkModule.getMovieDetailData(movieId)

        override suspend fun parseActorsData(
            movieId: Int,
            imagesResponse: ImagesResponse
        ): List<ActorResponse> =
            mainDataMoviesRepository.getActorsDataFromApi(
                movieId
            ).response.map { castActor ->
                ActorResponse(
                    castActor.id,
                    castActor.name,
                    castActor.character,
                    imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + castActor.imageUrl
                )
            }
    }
}