package com.example.moviesproject.data.remote.respones

import com.example.moviesproject.domain.model.ActorDetails
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ActorByIdResponse(

    @SerialName("also_known_as") val alsoKnownAs: List<String>,
    @SerialName("birthday") val birthday: String,
    @SerialName("gender") val gender: Int,
    @SerialName("imdb_id") val imdbId: String,
    @SerialName("known_for_department") val knownForDepartment: String,
    @SerialName("profile_path") val posterUrlPath: String,
    @SerialName("biography") val biography: String,
    @SerialName("deathday") val deathDay: String? = null,
    @SerialName("place_of_birth") val placeOfBirth: String,
    @SerialName("popularity") val popularity: Double,
    @SerialName("name") val name: String,
    @SerialName("id") val id: Int
)

fun ActorByIdResponse.toActor(): ActorDetails {

    return ActorDetails(
        id = id,
        birthday = birthday,
        gender = gender,
        genre = knownForDepartment,
        posterUrlPath = posterUrlPath,
        biography = biography,
        deathDay = deathDay,
        placeOfBirth = placeOfBirth,
        name = name
    )

}
