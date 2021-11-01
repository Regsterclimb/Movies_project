package com.example.moviesproject.domain.model

data class Actor(

    val id: Int,

    val birthday: String,

    val gender: Int,

    val genre: String,

    val posterUrlPath: String,

    val biography: String,

    val deathday: String? = null,

    val placeOfBirth: String,

    val name: String,

)
