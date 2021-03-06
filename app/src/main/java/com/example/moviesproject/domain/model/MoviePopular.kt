package com.example.moviesproject.domain.model

import com.example.moviesproject.data.remote.respones.MovieResponse

data class MoviePopular(
    val page: Int,
    val totalPages: Int,
    val results: List<MovieResponse>,
    val totalResults: Int
)