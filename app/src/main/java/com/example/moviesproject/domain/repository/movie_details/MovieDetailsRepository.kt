package com.example.moviesproject.domain.repository.movie_details

import com.example.moviesproject.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun loadMovieDetails(movieId:Int) : MovieDetails
}