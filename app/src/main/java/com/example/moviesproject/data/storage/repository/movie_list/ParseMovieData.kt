package com.example.moviesproject.data.storage.repository.movie_list

import com.example.moviesproject.data.model.MovieData
import com.example.moviesproject.data.remote.respones.GenreResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieResponse

interface ParseMovieData {

    fun parse(
        dataListResultMovieResponse: List<MovieResponse>,
        genreResponseData: List<GenreResponse>,
        imagesResponse: ImagesResponse
    ): List<MovieData>

    class Base : ParseMovieData {

        override fun parse(
            dataListResultMovieResponse: List<MovieResponse>,
            genreResponseData: List<GenreResponse>,
            imagesResponse: ImagesResponse
        ): List<MovieData> = dataListResultMovieResponse.map { movieResponse ->
            MovieData(
                movieResponse.id,
                movieResponse.title,
                movieResponse.storyLine,
                imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + movieResponse.posterPathUrl,
                movieResponse.backdropPathUrl,
                rating = (movieResponse.ratings / 2).toInt(),
                movieResponse.voteCount,
                pgAge = if (movieResponse.adult) 16 else 13,
                movieResponse.releaseDate,
                genreResponses = genreResponseData.filter { genreResponse ->
                    movieResponse.genreIds.contains(genreResponse.id)
                },
                isLiked = false
            )
        }
    }
}