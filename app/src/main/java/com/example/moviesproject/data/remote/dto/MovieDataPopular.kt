package com.example.moviesproject.data.remote.dto

import com.example.moviesproject.domain.model.MoviePopular
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDataPopular( // nullable or not?

	@SerialName("page")
	val page: Int,

	@SerialName("total_pages")
	val totalPages: Int,

	@SerialName("results")
	val results: List<ResultsMovie>,

	@SerialName("total_results")
	val totalResults: Int
)
fun MovieDataPopular.toMoviePopular(){

	MoviePopular(
		page = page,
		totalPages = totalPages,
		results = results,
		totalResults = totalResults
	)

}
