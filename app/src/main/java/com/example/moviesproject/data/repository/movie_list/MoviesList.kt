package com.example.moviesproject.data.repository.movie_list

interface MoviesList {
    suspend fun load(
        mainMoviesRepository: MainMoviesRepository,
        parseMovie: ParseMovie
    ): List<MovieData>

    class Base : MoviesList {

        override suspend fun load(
            mainMoviesRepository: MainMoviesRepository,
            parseMovie: ParseMovie
        ): List<MovieData> = parseMovie.parse(
            mainMoviesRepository.loadMoviesApi().results,
            mainMoviesRepository.loadGenresApi(),
            mainMoviesRepository.loadConfigurationApi()
        )
    }
}