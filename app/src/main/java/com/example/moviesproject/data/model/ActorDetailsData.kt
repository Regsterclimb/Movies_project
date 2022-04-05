package com.example.moviesproject.data.model

data class ActorDetailsData(
    val alsoKnownAs: List<String>,
    val birthday: String,
    val gender: Int,
    val imdbId: String,
    val knownForDepartment: String,
    val posterUrlPath: String,
    val biography: String,
    val deathDay: String? = null,
    val placeOfBirth: String,
    val popularity: Double,
    val name: String,
    val id: Int
)
