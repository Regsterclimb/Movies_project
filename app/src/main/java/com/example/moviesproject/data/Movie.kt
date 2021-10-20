package com.example.moviesproject.data

data class Movie(
     val id: Int,
     val title: String,
     val storyLine: String,
     val imageUrl: String,
     val detailImageUrl: String,
     val rating: Int,
     val reviewCount: Int,
     val pgAge: Int,
     val runningTime: Int,
     val genres: List<Genre>,
     val actors: List<Actor>,
     val isLiked: Boolean)

