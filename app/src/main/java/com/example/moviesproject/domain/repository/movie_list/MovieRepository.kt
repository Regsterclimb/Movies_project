package com.example.moviesproject.domain.repository.movie_list

import com.example.moviesproject.data.model.MovieData

interface MovieRepository {
    suspend fun loadMoviesList() : List<MovieData>

    suspend fun loadFreshMovieList() : List<MovieData>
}