package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.Movie

interface GetMovieRepository {

    suspend fun loadMovies(): List<Movie>

}