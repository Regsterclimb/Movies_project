package com.example.moviesproject.data.di

import com.example.moviesproject.data.repository.movie_list.MovieData

interface MovieDataRepository {
    suspend fun getMoviesDataList(): List<MovieData>
}