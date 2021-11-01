package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.MovieDetails

interface GetMovieDetailsRepository {

    suspend fun loadMovie(movieId: Int): MovieDetails

}