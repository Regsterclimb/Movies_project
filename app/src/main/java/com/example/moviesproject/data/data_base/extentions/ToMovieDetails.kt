package com.example.moviesproject.data.data_base.extentions

import com.example.moviesproject.data.data_base.entity.MovieDetailsEntity
import com.example.moviesproject.domain.model.MovieDetails

fun MovieDetailsEntity.toMovieDetails(): MovieDetails = MovieDetails(
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