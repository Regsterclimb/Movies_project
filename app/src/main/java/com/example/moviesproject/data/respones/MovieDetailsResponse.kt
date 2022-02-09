package com.example.moviesproject.data.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailsResponse(
    @SerialName("id") val id: Int,
    @SerialName("title") val title: String,
    @SerialName("backdrop_path") val backdropImageUrlPath: String,
    @SerialName("revenue") val revenue: Int,
    @SerialName("genres") val genreResponses: List<GenreResponse>,
    @SerialName("vote_count") val voteCount: Int,
    @SerialName("budget") val budget: Int,
    @SerialName("overview") val overview: String,
    @SerialName("original_title") val originalTitle: String,
    @SerialName("runtime") val runtime: Int,
    @SerialName("poster_path") val posterImageUrlPath: String,
    @SerialName("release_date") val releaseDate: String,
    @SerialName("vote_average") val voteAverage: Double,
    @SerialName("tagline") val tagline: String,
    @SerialName("adult") val adult: Boolean,
    @SerialName("status") val status: String
)