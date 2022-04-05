package com.example.moviesproject.domain.extentions

import com.example.moviesproject.data.model.ActorDetailsData
import com.example.moviesproject.domain.model.ActorDetails

fun ActorDetailsData.toActorsDetails(): ActorDetails = ActorDetails(
    alsoKnownAs,
    birthday,
    gender,
    imdbId,
    knownForDepartment,
    posterUrlPath,
    biography,
    deathDay,
    placeOfBirth,
    popularity,
    name,
    id
)