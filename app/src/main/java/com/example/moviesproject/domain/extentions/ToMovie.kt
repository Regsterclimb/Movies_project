package com.example.moviesproject.domain.extentions

import com.example.moviesproject.data.model.MovieData
import com.example.moviesproject.domain.model.Movie

fun MovieData.toMovie(): Movie = Movie(
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