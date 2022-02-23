package com.example.moviesproject.data.repository.movie_list

interface MoviesDataList {
    suspend fun load(): List<MovieData>

    class Base(
        private val mainDataMoviesRepository: MainDataMoviesRepository,
        private val parseMovieData: ParseMovieData
    ) : MoviesDataList {

        override suspend fun load(): List<MovieData> = parseMovieData.parse(
            mainDataMoviesRepository.loadMoviesApi().results,
            mainDataMoviesRepository.loadGenresApi(),
            mainDataMoviesRepository.loadConfigurationApi()
        )
    }
}