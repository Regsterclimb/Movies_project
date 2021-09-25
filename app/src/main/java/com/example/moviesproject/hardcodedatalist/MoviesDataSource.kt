package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.R
import com.example.moviesproject.data.Movie

class MoviesDataSource {

    fun getMovies () : List<Movie> {
        return listOf(
            Movie("Action, Adventure, Fantasy","123 REVIEWS", "Avengers: End Game", "137 MIN", R.drawable.movie_avengers_icon_fragment),
            Movie("Action, Adventure, Fantasy","123 REVIEWS", "Tenet", "137 MIN",R.drawable.movie_tenet_icon_fragment ),
            Movie("Action, Adventure, Fantasy","123 REVIEWS", "Black Widow", "137 MIN",R.drawable.movie_tenet_icon_fragment ),
            Movie("Action, Adventure, Fantasy","123 REVIEWS", "Luxury Girl", "137 MIN",R.drawable.movie_tenet_icon_fragment )
        )


    }

}