package com.example.moviesproject.domain.model

import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.GenreResponse

data class MovieDetails(
    val id: Int,
    val title: String,
    val backdropImageUrlPath: String,
    val revenue: Int,
    val genreResponses: List<GenreResponse>,
    val voteCount: Int,
    val budget: Int,
    val storyLine: String,
    val runtime: Int,
    val posterImageUrlPath: String,
    val releaseDate: String,
    val rating: Int,
    val tagline: String,
    val actorResponseList: List<ActorResponse>,
    val pgAge: String
)
