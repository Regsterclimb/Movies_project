package com.example.moviesproject.data.remote.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorResponse(
    @SerialName("id") val id: Int,
    @SerialName("character") val character: String,
    @SerialName("name") val name: String,
    @SerialName("profile_path") val imageUrl: String? = null
)

