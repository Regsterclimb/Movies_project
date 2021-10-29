package com.example.moviesproject.data.actors

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCastsData(

	@SerialName("cast")
	val cast: List<CastActor>,

	@SerialName("id")
	val id: Int,

)

@Serializable
data class CastActor(

	@SerialName("id")
	val id: Int,

	@SerialName("character")
	val character: String,

	@SerialName("name")
	val name: String,

	@SerialName("profile_path")
	val imageUrl: String? = null

)
