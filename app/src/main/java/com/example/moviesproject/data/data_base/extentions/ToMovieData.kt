package com.example.moviesproject.data.data_base.extentions

import com.example.moviesproject.data.data_base.entity.MovieEntity
import com.example.moviesproject.data.repository.movie_list.MovieData

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