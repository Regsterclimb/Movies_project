package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.extentions.toMovieDetails
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.repository.movie_details.MovieDetailsRepository
import com.example.moviesproject.domain.use_cases.logic.MovieDetailsLogic

class MovieDetailsUseCase(
    private val movieDetailsRepository: MovieDetailsRepository,
    private val movieDetailsLogic: MovieDetailsLogic
) {
    sealed class DetailsResult {
        class Success(val movieDetails: MovieDetails) : DetailsResult()
        class Error(val error: String) : DetailsResult()
    }

    suspend fun getDetailsResult(movieId: Int): DetailsResult = movieDetailsLogic.execute {
        movieDetailsRepository.loadMovieDetails(movieId).toMovieDetails()
    }

    suspend fun getFreshDetailsResult(movieId: Int): DetailsResult = movieDetailsLogic.execute {
        movieDetailsRepository.loadFreshMovieDetails(movieId).toMovieDetails()
    }
}