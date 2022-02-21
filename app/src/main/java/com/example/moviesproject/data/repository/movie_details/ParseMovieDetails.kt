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
                id = movieDetailsResponse.id, //  id : Int
                title = movieDetailsResponse.title, // Main title : String
                detailImageUrl = imagesResponse.baseUrl + imagesResponse.backdropSizes[3] + movieDetailsResponse.backdropImageUrlPath,
                revenue = movieDetailsResponse.revenue, //
                genreResponses = movieDetailsResponse.genreResponses, // List<Genre>
                reviewCount = movieDetailsResponse.voteCount, // double "8.2"
                budget = movieDetailsResponse.budget, // Int
                storyLine = movieDetailsResponse.overview,
                runtime = movieDetailsResponse.runtime,
                imageUrl = imagesResponse.baseUrl + imagesResponse.posterSizes[4] + movieDetailsResponse.posterImageUrlPath,
                releaseDate = movieDetailsResponse.releaseDate,
                rating = (movieDetailsResponse.voteAverage / 2).toInt(),
                tagLine = movieDetailsResponse.tagline,
                actorResponseList = listOfActorResponse, // Warning wrong Url
                pgAge = if (movieDetailsResponse.adult) "13" else "16"
            )
        }
    }
}