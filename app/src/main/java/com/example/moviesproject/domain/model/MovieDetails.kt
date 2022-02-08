package com.example.moviesproject.domain.model

import com.example.moviesproject.data.respones.ActorResponse
import com.example.moviesproject.data.respones.Genre

data class MovieDetails(
    val id: Int,
    val title: String,
    val backdropImageUrlPath: String,
    val revenue: Int,
    val genres: List<Genre>,
    val voteCount: Int,
    val budget: Int,
    val overview: String,
    val runtime: Int,
    val posterImageUrlPath: String,
    val releaseDate: String,
    val voteAverage: Int,
    val tagline: String,
    val actorResponseList: List<ActorResponse>,
    val pgAge: String
)
