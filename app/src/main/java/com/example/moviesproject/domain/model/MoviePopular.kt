package com.example.moviesproject.domain.model

import com.example.moviesproject.data.remote.dto.ResultsMovie

data class MoviePopular(
    val page: Int,

    val totalPages: Int,

    val results: List<ResultsMovie>,

    val totalResults: Int
)
