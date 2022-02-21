package com.example.moviesproject.data.data_base.extentions

import com.example.moviesproject.data.data_base.entity.MovieEntity
import com.example.moviesproject.domain.model.Movie

fun MovieEntity.toMovie(): Movie = Movie(
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