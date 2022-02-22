package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.repository.movie_details.MovieDetailsRepository

class MovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository
) {
    suspend fun loadMovie(movieId: Int): MovieDetails =
        movieDetailsRepository.loadMovieDetails(movieId)
}