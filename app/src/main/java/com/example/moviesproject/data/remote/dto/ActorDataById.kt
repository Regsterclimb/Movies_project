package com.example.moviesproject.data.remote.dto

import com.example.moviesproject.domain.model.ActorDetails
import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ActorDataById(

	@field:SerializedName("also_known_as")
	val alsoKnownAs: List<String>,

	@field:SerializedName("birthday")
	val birthday: String,

	@field:SerializedName("gender")
	val gender: Int,

	@field:SerializedName("imdb_id")
	val imdbId: String,

	@field:SerializedName("known_for_department")
	val knownForDepartment: String,

	@field:SerializedName("profile_path")
	val posterUrlPath: String,

	@field:SerializedName("biography")
	val biography: String,

	@field:SerializedName("deathday")
	val deathday: String? = null,

	@field:SerializedName("place_of_birth")
	val placeOfBirth: String,

	@field:SerializedName("popularity")
	val popularity: Double,

	@field:SerializedName("name")
	val name: String,

	@field:SerializedName("id")
	val id: Int,

)

fun ActorDataById.toActor() : ActorDetails {

	return ActorDetails(
		id = id,
		birthday = birthday,
		gender = gender,
		genre = knownForDepartment,
		posterUrlPath = posterUrlPath,
		biography = biography,
		deathday = deathday,
		placeOfBirth = placeOfBirth,
		name = name
	)

}
