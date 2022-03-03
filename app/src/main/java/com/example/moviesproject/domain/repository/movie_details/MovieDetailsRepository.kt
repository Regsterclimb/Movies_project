package com.example.moviesproject.domain.repository.movie_details

import com.example.moviesproject.data.model.MovieDetailsData

interface MovieDetailsRepository {
    suspend fun loadMovieDetails(movieId:Int) : MovieDetailsData

    suspend fun loadFreshMovieDetails(movieId:Int) : MovieDetailsData
}