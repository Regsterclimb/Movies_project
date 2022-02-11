package com.example.moviesproject.data.remote.network_module

import com.example.moviesproject.data.remote.respones.MovieConfigurationResponse
import com.example.moviesproject.data.remote.respones.MovieGenresResponse
import com.example.moviesproject.data.remote.respones.MoviePopularResponse
import retrofit2.http.GET

interface MoviesApi {
    @GET("genre/movie/list?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US")
    suspend fun loadDataGenres(): MovieGenresResponse

    @GET("configuration?api_key=29d669e3884b3c81259c3e02780bcec9")
    suspend fun loadDataConfiguration(): MovieConfigurationResponse

    @GET("movie/popular?api_key=29d669e3884b3c81259c3e02780bcec9&language=en-US&page=1")
    suspend fun loadDataMoviesList(): MoviePopularResponse
}