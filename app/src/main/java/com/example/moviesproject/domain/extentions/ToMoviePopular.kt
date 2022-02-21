package com.example.moviesproject.domain.extentions

import com.example.moviesproject.data.remote.respones.MoviePopularResponse
import com.example.moviesproject.domain.model.MoviePopular

fun MoviePopularResponse.toMoviePopular(): MoviePopular = MoviePopular(
    page,
    totalPages,
    results,
    totalResults
)