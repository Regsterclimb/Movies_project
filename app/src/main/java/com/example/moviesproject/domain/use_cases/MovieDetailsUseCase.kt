package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.repository.movie_details.MovieDetailsRepository

class MovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val movieDetailsCatch: MovieDetailsCatch
) {
    //TODO()
    sealed class DetailsResult {
        data class Success(val movieDetails: MovieDetails) : DetailsResult()
        data class Error(val error: String) : DetailsResult()
    }

    suspend fun getDetailsResult(movieId: Int): DetailsResult = movieDetailsCatch.execute {
        movieDetailsRepository.loadMovieDetails(movieId)
    }

    suspend fun getFreshDetailsResult(movieId: Int): DetailsResult = movieDetailsCatch.execute {
        movieDetailsRepository.loadFreshMovieDetails(movieId)
    }
}