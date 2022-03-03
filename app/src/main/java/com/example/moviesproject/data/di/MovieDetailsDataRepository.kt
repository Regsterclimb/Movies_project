package com.example.moviesproject.data.di

import com.example.moviesproject.data.model.MovieDetailsData

interface MovieDetailsDataRepository {
    suspend fun loadMovieDetailsData(movieId: Int): MovieDetailsData

    suspend fun loadFreshDetailsData(movieId: Int): MovieDetailsData
}