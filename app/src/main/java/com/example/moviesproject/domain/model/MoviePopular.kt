package com.example.moviesproject.domain.model

import com.example.moviesproject.data.respones.MoviePopularResponse
import com.example.moviesproject.data.respones.MovieResponse

data class MoviePopular(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieResponse>,
    val totalResults: Int
)

fun MoviePopularResponse.toMoviePopular(): MoviePopular =
    MoviePopular(page, totalPages, results, totalResults)
