package com.example.moviesproject.data.storage.extentions

import com.example.moviesproject.data.model.MovieDetailsData
import com.example.moviesproject.data.storage.entity.MovieDetailsEntity

fun MovieDetailsEntity.toMovieDetailsData(): MovieDetailsData = MovieDetailsData(
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