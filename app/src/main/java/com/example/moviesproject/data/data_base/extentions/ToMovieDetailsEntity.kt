package com.example.moviesproject.domain.extentions

import com.example.moviesproject.data.data_base.entity.MovieDetailsEntity
import com.example.moviesproject.data.repository.movie_details.MovieDetailsData

fun MovieDetailsData.toMovieDetailsEntity(): MovieDetailsEntity = MovieDetailsEntity(
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