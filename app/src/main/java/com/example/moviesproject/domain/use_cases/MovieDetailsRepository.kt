package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.data.repository.movie_details.MovieDetailsData

interface MovieDetailsRepository {
    suspend fun loadMovieDetails(movieId: Int): MovieDetailsData
}