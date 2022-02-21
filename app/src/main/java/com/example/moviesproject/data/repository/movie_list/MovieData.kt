package com.example.moviesproject.data.repository.movie_list

import com.example.moviesproject.data.remote.respones.GenreResponse

data class MovieData(
    val id: Int,
    val title: String,
    val storyLine: String,
    val imageUrl: String,
    val detailImageUrl: String,
    val rating: Int,
    val reviewCount: Int,
    val pgAge: Int,
    val releaseDate: String,
    val genreResponses: List<GenreResponse>,
    val isLiked: Boolean
)
