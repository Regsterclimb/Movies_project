package com.example.moviesproject.data.repository.movie_list

interface MoviesDataList {
    suspend fun load(
        mainDataMoviesRepository: MainDataMoviesRepository,
        parseMovieData: ParseMovieData
    ): List<MovieData>

    class Base : MoviesDataList {

        override suspend fun load(
            mainDataMoviesRepository: MainDataMoviesRepository,
            parseMovieData: ParseMovieData
        ): List<MovieData> = parseMovieData.parse(
            mainDataMoviesRepository.loadMoviesApi().results,
            mainDataMoviesRepository.loadGenresApi(),
            mainDataMoviesRepository.loadConfigurationApi()
        )
    }
}