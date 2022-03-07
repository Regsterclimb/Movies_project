package com.example.moviesproject.data.remote.network_module

import com.example.moviesproject.data.remote.respones.MovieCastsResponse
import com.example.moviesproject.data.remote.respones.MovieDetailsResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieDetailsApi {
    @GET("movie/{movieId}?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US)")
    suspend fun loadDataMovieDetailsById(
        @Path("movieId") id: Int
    ): MovieDetailsResponse

    @GET("movie/{movieId}/credits?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US")
    suspend fun loadDataMovieCastActorsById(
        @Path("movieId") id: Int
    ): MovieCastsResponse
}