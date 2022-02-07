package com.example.moviesproject.data.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDataGenres(

	@SerialName("genres")
	val genres: List<Genre>
)

@Serializable
data class Genre(
	@SerialName("name")
	val name: String,
	@SerialName("id")
	val id: Int
)
