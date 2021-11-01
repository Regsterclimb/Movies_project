package com.example.moviesproject.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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

