package com.example.moviesproject.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDataGenres(

	@SerialName("genres")
	val genres: List<GenresItem>
)

@Serializable
data class GenresItem(

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: Int
)
