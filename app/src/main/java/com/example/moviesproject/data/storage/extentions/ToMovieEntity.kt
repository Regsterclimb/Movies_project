package com.example.moviesproject.data.storage.extentions

import com.example.moviesproject.data.model.MovieData
import com.example.moviesproject.data.storage.entity.MovieEntity

fun MovieData.toMovieEntity(): MovieEntity = MovieEntity(
    id,
    title,
    storyLine,
    imageUrl,
    detailImageUrl,
    rating,
    reviewCount,
    pgAge,
    releaseDate,
    genreResponses,
    isLiked
)