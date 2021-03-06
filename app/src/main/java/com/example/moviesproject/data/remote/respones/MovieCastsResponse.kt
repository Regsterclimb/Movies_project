package com.example.moviesproject.data.remote.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCastsResponse(
	@SerialName("cast") val response: List<ActorResponse>,
	@SerialName("id") val id: Int,
	)

