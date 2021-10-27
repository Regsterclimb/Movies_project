package com.example.moviesproject.data

import com.google.gson.annotations.SerializedName


data class Response(

	@field:SerializedName("images")
	val images: Images,

	@field:SerializedName("change_keys")
	val changeKeys: List<String>
)

data class Images(

	@field:SerializedName("poster_sizes")
	val posterSizes: List<String>,

	@field:SerializedName("secure_base_url")
	val secureBaseUrl: String,

	@field:SerializedName("backdrop_sizes")
	val backdropSizes: List<String>,

	@field:SerializedName("base_url")
	val baseUrl: String,

	@field:SerializedName("logo_sizes")
	val logoSizes: List<String>,

	@field:SerializedName("still_sizes")
	val stillSizes: List<String>,

	@field:SerializedName("profile_sizes")
	val profileSizes: List<String>

)
