package com.example.moviesproject.domain.extentions

import com.example.moviesproject.data.repository.movie_details.MovieDetailsData
import com.example.moviesproject.domain.model.MovieDetails

fun MovieDetailsData.toMovieDetails(): MovieDetails = MovieDetails(
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