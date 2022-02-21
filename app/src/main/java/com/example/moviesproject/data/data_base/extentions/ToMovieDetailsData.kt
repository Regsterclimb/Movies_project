package com.example.moviesproject.data.data_base.extentions

import com.example.moviesproject.data.data_base.entity.MovieDetailsEntity
import com.example.moviesproject.data.repository.movie_details.MovieDetailsData

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