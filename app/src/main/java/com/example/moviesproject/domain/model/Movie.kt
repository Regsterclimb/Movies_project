package com.example.moviesproject.domain.model

import com.example.moviesproject.data.respones.Genre


data class Movie
    (
    val id: Int,

    val title: String,

    val storyLine: String,

    val imageUrl: String,

    val detailImageUrl: String,

    val rating: Int,

    val reviewCount: Int,

    val pgAge: Int,

    val releaseDate: String,

    val genres: List<Genre>,

    val isLiked: Boolean
)


