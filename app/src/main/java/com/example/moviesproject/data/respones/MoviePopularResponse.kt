package com.example.moviesproject.data.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoviePopularResponse( // nullable or not?
	@SerialName("page")
	val page: Int,
	@SerialName("total_pages")
	val totalPages: Int,
	@SerialName("results")
	val results: List<MovieResponse>,
	@SerialName("total_results")
	val totalResults: Int
)
