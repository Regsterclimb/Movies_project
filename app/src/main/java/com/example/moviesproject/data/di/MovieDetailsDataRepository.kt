package com.example.moviesproject.data.di

import com.example.moviesproject.data.repository.movie_details.MovieDetailsData

interface MovieDetailsDataRepository {
    suspend fun loadMovieDetailsData(movieId: Int): MovieDetailsData
}