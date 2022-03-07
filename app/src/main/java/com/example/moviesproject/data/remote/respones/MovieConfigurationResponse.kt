package com.example.moviesproject.data.remote.respones

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieConfigurationResponse(
	@SerialName("images")
	val images: ImagesResponse,
	@SerialName("change_keys")
	val changeKeys: List<String>
)
