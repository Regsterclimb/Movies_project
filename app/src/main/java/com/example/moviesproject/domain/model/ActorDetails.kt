package com.example.moviesproject.domain.model

data class ActorDetails(
    val id: Int,
    val birthday: String,
    val gender: Int,
    val genre: String,
    val posterUrlPath: String,
    val biography: String,
    val deathDay: String? = null,
    val placeOfBirth: String,
    val name: String,
)
