package com.example.moviesproject.data.configurationdata

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationMovieData(

	@SerialName("images")
	val images: ImagesData,

	@SerialName("change_keys")
	val changeKeys: List<String>
)

@Serializable
data class ImagesData(

	@SerialName("poster_sizes")
	val posterSizes: List<String>,

	@SerialName("secure_base_url")
	val secureBaseUrl: String,

	@SerialName("backdrop_sizes")
	val backdropSizes: List<String>,

	@SerialName("base_url")
	val baseUrl: String,

	@SerialName("logo_sizes")
	val logoSizes: List<String>,

	@SerialName("still_sizes")
	val stillSizes: List<String>,

	@SerialName("profile_sizes")
	val profileSizes: List<String>
)