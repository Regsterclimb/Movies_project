package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.MovieDetails

interface MovieDetailsRepository {
    suspend fun loadMovie(movieId: Int): MovieDetails
}