package com.example.moviesproject.data.repository.movie_list

import com.example.moviesproject.data.remote.respones.GenreResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieResponse

interface ParseMovie {

    fun parse(
        dataListResultMovieResponse: List<MovieResponse>,
        genreResponseData: List<GenreResponse>,
        imagesResponse: ImagesResponse
    ): List<MovieData>

    class Base : ParseMovie {

        override fun parse(
            dataListResultMovieResponse: List<MovieResponse>,
            genreResponseData: List<GenreResponse>,
            imagesResponse: ImagesResponse
        ): List<MovieData> = dataListResultMovieResponse.map { jsonMovie ->
            MovieData(
                jsonMovie.id,
                jsonMovie.title,
                jsonMovie.storyLine,
                imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + jsonMovie.posterPathUrl,
                jsonMovie.backdropPathUrl,
                rating = (jsonMovie.ratings / 2).toInt(),
                jsonMovie.voteCount,
                pgAge = if (jsonMovie.adult) 16 else 13,
                jsonMovie.releaseDate,
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