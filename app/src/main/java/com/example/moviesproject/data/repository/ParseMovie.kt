package com.example.moviesproject.data.repository

import com.example.moviesproject.data.respones.GenreResponse
import com.example.moviesproject.data.respones.ImagesResponse
import com.example.moviesproject.data.respones.MovieResponse
import com.example.moviesproject.domain.model.Movie

interface ParseMovie {

    fun parse(
        dataListResultMovieResponse: List<MovieResponse>,
        genreResponseData: List<GenreResponse>,
        imagesResponse: ImagesResponse
    ): List<Movie>

    class Base : ParseMovie {
        override fun parse(
            dataListResultMovieResponse: List<MovieResponse>,
            genreResponseData: List<GenreResponse>,
            imagesResponse: ImagesResponse
        ): List<Movie> = dataListResultMovieResponse.map { jsonMovie ->
            Movie(
                id = jsonMovie.id,
                title = jsonMovie.title,
                storyLine = jsonMovie.storyLine,
                imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + jsonMovie.posterPathUrl,
                detailImageUrl = jsonMovie.backdropPathUrl,
                rating = (jsonMovie.ratings / 2).toInt(),
                reviewCount = jsonMovie.voteCount,
                pgAge = if (jsonMovie.adult) 16 else 13,
                releaseDate = jsonMovie.releaseDate,
                genreResponses = jsonMovie.genreIds.map { id ->
                    genreResponseData.associateBy(GenreResponse::id)[id].orThrow {
                        IllegalArgumentException(
                            "Genre not found"
                        )
                    }
                },
                isLiked = false
            )
        }

        private fun <T : Any> T?.orThrow(createThrowable: () -> Throwable): T {
            return this ?: throw createThrowable()
        }
    }
}