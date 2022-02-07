package com.example.moviesproject.data.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCastsResponse(
	@SerialName("cast")
	val cast: List<CastActor>,
	@SerialName("id")
	val id: Int,
	)

