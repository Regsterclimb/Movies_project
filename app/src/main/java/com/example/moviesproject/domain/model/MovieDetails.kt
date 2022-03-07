package com.example.moviesproject.domain.model

import com.example.moviesproject.data.remote.respones.ActorResponse

data class MovieDetails(
    val id: Int,
    val title: String,
    val detailImageUrl: String,
    val revenue: Int,
    val genreResponses: String,
    val reviewCount: String,
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