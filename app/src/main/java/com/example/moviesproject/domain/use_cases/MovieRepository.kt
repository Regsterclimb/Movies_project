package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.domain.model.Movie

interface MovieRepository {

    suspend fun loadMovies(): List<Movie>

}