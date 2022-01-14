package com.example.moviesproject.domain.model

import com.example.moviesproject.data.remote.dto.CastActor
import com.example.moviesproject.data.remote.dto.Genre

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

    val actorList: List<CastActor>,

    val pgAge: String
)
