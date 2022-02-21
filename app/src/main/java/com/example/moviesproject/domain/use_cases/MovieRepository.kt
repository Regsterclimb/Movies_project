package com.example.moviesproject.domain.use_cases

import com.example.moviesproject.data.repository.movie_list.MovieData

interface MovieRepository {
    suspend fun getMoviesList(): List<MovieData>
}