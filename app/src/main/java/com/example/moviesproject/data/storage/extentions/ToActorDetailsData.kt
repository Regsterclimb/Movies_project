package com.example.moviesproject.data.storage.extentions

import com.example.moviesproject.data.model.ActorDetailsData
import com.example.moviesproject.data.remote.respones.ActorByIdResponse

fun ActorByIdResponse.toActorDetailsData(): ActorDetailsData =
    ActorDetailsData(
        alsoKnownAs,
        birthday = birthday ?: "",
        gender,
        imdbId ?: "",
        knownForDepartment,
        posterUrlPath ?: "",
        biography,
        deathDay = deathDay ?: "",
        placeOfBirth = placeOfBirth ?: "",
        popularity,
        name,
        id
    )