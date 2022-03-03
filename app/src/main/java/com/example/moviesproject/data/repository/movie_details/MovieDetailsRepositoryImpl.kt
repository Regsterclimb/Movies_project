package com.example.moviesproject.data.repository.movie_details

import com.example.moviesproject.data.di.MovieDetailsDataRepository
import com.example.moviesproject.data.model.MovieDetailsData
import com.example.moviesproject.domain.repository.movie_details.MovieDetailsRepository

class MovieDetailsRepositoryImpl(
    private val movieDetailsDataRepository: MovieDetailsDataRepository
) : MovieDetailsRepository {

    override suspend fun loadMovieDetails(movieId: Int): MovieDetailsData =
        movieDetailsDataRepository.loadMovieDetailsData(movieId)

    override suspend fun loadFreshMovieDetails(movieId: Int): MovieDetailsData =
        movieDetailsDataRepository.loadFreshDetailsData(movieId)
}