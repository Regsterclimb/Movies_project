package com.example.moviesproject.domain.repository.movie_details

import android.util.Log
import com.example.moviesproject.data.di.MovieDetailsDataRepository
import com.example.moviesproject.domain.extentions.toMovieDetails
import com.example.moviesproject.domain.model.MovieDetails

class MovieDetailsRepositoryImpl(
    private val movieDetailsDataRepository: MovieDetailsDataRepository
) : MovieDetailsRepository {

    override suspend fun loadMovieDetails(movieId: Int): MovieDetails =
        movieDetailsDataRepository.loadMovieDetailsData(movieId).toMovieDetails()
    init {
        Log.d("initStart", "MovieDetailsRepositoryImpl")
    }
    protected fun finalize() {
        Log.d("initStart", "MovieDetailsRepositoryImpl finalize")
    }
}