package com.example.moviesproject.data.storage.extentions

import com.example.moviesproject.data.model.MovieData
import com.example.moviesproject.data.storage.entity.MovieEntity

fun MovieEntity.toMovieData(): MovieData = MovieData(
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