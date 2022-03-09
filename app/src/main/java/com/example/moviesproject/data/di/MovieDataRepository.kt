package com.example.moviesproject.data.di

import com.example.moviesproject.data.model.MovieData

interface MovieDataRepository {
    suspend fun getMoviesDataList(): List<MovieData>

    suspend fun getFreshMovieDataList(): List<MovieData>

    suspend fun saveMoviesListDb(list: List<MovieData>)
}