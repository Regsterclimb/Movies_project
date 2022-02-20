package com.example.moviesproject.domain.model

import com.example.moviesproject.data.data_base.entity.MovieDetailsEntity
import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.GenreResponse

data class MovieDetails(
    val id: Int,
    val title: String,
    val detailImageUrl: String,
    val revenue: Int,
    val genreResponses: List<GenreResponse>,
    val reviewCount: Int,
    val budget: Int,
    val storyLine: String,
    val runtime: Int,
    val imageUrl: String,
    val releaseDate: String,
    val rating: Int,
    val tagLine: String,
    val actorResponseList: List<ActorResponse>,
    val pgAge: String
)

fun MovieDetails.toMovieEntity(): MovieDetailsEntity = MovieDetailsEntity(
    id,
    title,
    detailImageUrl,
    revenue,
    genreResponses,
    reviewCount,
    budget,
    storyLine,
    runtime,
    imageUrl,
    releaseDate,
    rating,
    tagLine,
    actorResponseList,
    pgAge
)
