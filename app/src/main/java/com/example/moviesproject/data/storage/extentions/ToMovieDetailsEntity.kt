package com.example.moviesproject.domain.extentions

import com.example.moviesproject.data.model.MovieDetailsData
import com.example.moviesproject.data.storage.entity.MovieDetailsEntity

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