package com.example.moviesproject.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ResultsMovie(

    @SerialName("overview")
    val storyLine: String,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val title1: String,

    @SerialName("video")
    val video: Boolean,

    @SerialName("title")
    val title: String,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    @SerialName("poster_path")
    val posterPathUrl: String,

    @SerialName("backdrop_path")
    val backdropPathUrl: String,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("popularity")
    val popularity: Double,

    @SerialName("vote_average")
    val ratings: Double,

    @SerialName("id")
    val id: Int,

    @SerialName("adult")
    val adult: Boolean,

    @SerialName("vote_count")
    val voteCount: Int

)