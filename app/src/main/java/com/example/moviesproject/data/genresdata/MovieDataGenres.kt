package com.example.moviesproject.data.genresdata

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDataGenres(

	@field:SerializedName("genres")
	val genres: List<Genre>
)

@Serializable
data class Genre(

	@SerialName("name")
	val name: String,

	@SerialName("id")
	val id: Int
)
