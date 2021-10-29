package com.example.moviesproject.data.moviedata

import com.example.moviesproject.data.actors.CastActor
import com.example.moviesproject.data.genresdata.Genre

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

    val voteAverage: Double,

    val tagline: String,

    val adult: Boolean,

    val status: String,

    val actorList: List<CastActor>,

    val pgAge: String
)
