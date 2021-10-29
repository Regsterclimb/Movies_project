package com.example.moviesproject.data.moviedata

import com.example.moviesproject.data.genresdata.Genre

data class MoviePopular(
    val page: Int,
    val totalPages: Int,
    val results: List<ResultsMovie>,
    val totalResults: Int
)

data class Movie(
    val id: Int,
    val title: String,
    val storyLine: String,
    val imageUrl: String,
    val detailImageUrl: String,
    val rating: Int,
    val reviewCount: Int,
    val pgAge: Int,
    val releaseDate: String,
    val genres: List<Genre>,
    val isLiked: Boolean)


