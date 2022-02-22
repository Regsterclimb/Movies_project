package com.example.moviesproject.domain.repository.movie_list

import com.example.moviesproject.domain.model.Movie

interface MovieRepository {
    suspend fun loadMoviesList() : List<Movie>
    suspend fun loadFreshMovieList() : List<Movie>
}