package com.example.moviesproject.data.repository.movie_details

import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieDetailsResponse

interface ParseMovieDetails {

    fun parse(
        movieDetailsResponse: MovieDetailsResponse,
        imagesResponse: ImagesResponse,
        listOfActorResponse: List<ActorResponse>
    ): MovieDetailsData

    class Base : ParseMovieDetails {

        override fun parse(
            movieDetailsResponse: MovieDetailsResponse,
            imagesResponse: ImagesResponse,
            listOfActorResponse: List<ActorResponse>
        ): MovieDetailsData {
            return MovieDetailsData(
                movieDetailsResponse.id,
                movieDetailsResponse.title,
                detailImageUrl = imagesResponse.baseUrl + imagesResponse.backdropSizes[3] + movieDetailsResponse.backdropImageUrlPath,
                movieDetailsResponse.revenue,
                movieDetailsResponse.genreResponses,
                movieDetailsResponse.voteCount,
                movieDetailsResponse.budget,
                movieDetailsResponse.overview,
                movieDetailsResponse.runtime,
                imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + movieDetailsResponse.posterImageUrlPath,
                movieDetailsResponse.releaseDate,
                rating = (movieDetailsResponse.voteAverage / 2).toInt(),
                tagLine = movieDetailsResponse.tagline,
                actorResponseList = listOfActorResponse,
                pgAge = if (movieDetailsResponse.adult) "13" else "16"
            )
        }
    }
}