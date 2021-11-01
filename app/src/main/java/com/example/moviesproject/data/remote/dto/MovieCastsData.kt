package com.example.moviesproject.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieCastsData(

	@SerialName("cast")
	val cast: List<CastActor>,

	@SerialName("id")
	val id: Int,
	)

