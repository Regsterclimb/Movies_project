package com.example.moviesproject.data.remote.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationMovieData(

	@SerialName("images")
	val images: ImagesData,

	@SerialName("change_keys")
	val changeKeys: List<String>

)
