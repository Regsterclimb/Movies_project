package com.example.moviesproject.domain.repository.movie_details

import com.example.moviesproject.data.di.MovieDetailsDataRepository
import com.example.moviesproject.domain.extentions.toMovieDetails
import com.example.moviesproject.domain.model.MovieDetails

class MovieDetailsRepositoryImpl(
    private val movieDetailsDataRepository: MovieDetailsDataRepository
) : MovieDetailsRepository {

    override suspend fun loadMovieDetails(movieId: Int): MovieDetails =
        movieDetailsDataRepository.loadMovieDetailsData(movieId).toMovieDetails()

    override suspend fun loadFreshMovieDetails(movieId: Int): MovieDetails =
        movieDetailsDataRepository.loadFreshDetailsData(movieId).toMovieDetails()
}