package com.example.moviesproject.data.remote.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieGenresResponse(
    @SerialName("genres") val genreResponses: List<GenreResponse>
)

@Serializable
data class GenreResponse(
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int
)
