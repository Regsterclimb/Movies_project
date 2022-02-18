package com.example.moviesproject.domain.model

import com.example.moviesproject.data.data_base.entity.MovieEntity
import com.example.moviesproject.data.remote.respones.GenreResponse


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
    val genreResponses: List<GenreResponse>,
    val isLiked: Boolean
)

fun Movie.toMovieEntity(): MovieEntity = MovieEntity(
    id,
    title,
    storyLine,
    imageUrl,
    detailImageUrl,
    rating,
    reviewCount,
    pgAge,
    releaseDate,
    genreResponses,
    isLiked
)


