package com.example.moviesproject.domain.extentions

import com.example.moviesproject.data.model.MovieDetailsData
import com.example.moviesproject.domain.model.MovieDetails

fun MovieDetailsData.toMovieDetails(): MovieDetails = MovieDetails(
    id,
    title,
    detailImageUrl,
    revenue,
    genreResponses = genreResponses.joinToString(", ", "", "") {
        it.name
    },
    reviewCount = reviewCount.toString(),
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