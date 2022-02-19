package com.example.moviesproject.data.repository.movie_details

import com.example.moviesproject.data.remote.respones.ActorResponse
import com.example.moviesproject.data.remote.respones.ImagesResponse
import com.example.moviesproject.data.remote.respones.MovieDetailsResponse
import com.example.moviesproject.domain.model.MovieDetails

interface ParseMovieDetails {

    fun parse(
        movieDetailsResponse: MovieDetailsResponse,
        imagesResponse: ImagesResponse,
        listOfActorResponse: List<ActorResponse>
    ): MovieDetails

    class Base : ParseMovieDetails {

        override fun parse(
            movieDetailsResponse: MovieDetailsResponse,
            imagesResponse: ImagesResponse,
            listOfActorResponse: List<ActorResponse>
        ): MovieDetails {
            return MovieDetails(
                id = movieDetailsResponse.id, //  id : Int
                title = movieDetailsResponse.title, // Main title : String
                backdropImageUrlPath = imagesResponse.baseUrl + imagesResponse.backdropSizes[3] + movieDetailsResponse.backdropImageUrlPath,
                revenue = movieDetailsResponse.revenue, //
                genreResponses = movieDetailsResponse.genreResponses, // List<Genre>
                voteCount = movieDetailsResponse.voteCount, // double "8.2"
                budget = movieDetailsResponse.budget, // Int
                storyLine = movieDetailsResponse.overview,
                runtime = movieDetailsResponse.runtime,
                posterImageUrlPath = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + movieDetailsResponse.posterImageUrlPath,
                releaseDate = movieDetailsResponse.releaseDate,
                voteAverage = (movieDetailsResponse.voteAverage / 2).toInt(),
                tagline = movieDetailsResponse.tagline,
                actorResponseList = listOfActorResponse, // Warning wrong Url
                pgAge = if (movieDetailsResponse.adult) "13" else "16"
            )
        }
    }
}