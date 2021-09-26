package com.example.moviesproject.hardcodedatalist

import com.example.moviesproject.R
import com.example.moviesproject.data.Movie

class MoviesDataSource {

    fun getMovies () : List<Movie> {
        return listOf(
            Movie("Action, Adventure, Drama","125 REVIEWS", "Avengers: End Game", "137 MIN", R.drawable.movie_avengers_icon_fragment),
            Movie("Action, Sci-Fi, Thriller","98 REVIEWS", "Tenet", "97 MIN",R.drawable.movie_tenet_icon_fragment),
            Movie("Action, Adventure, Sci-Fi","38 REVIEWS", "Black Widow", "102 MIN",R.drawable.movie_black_widow_icon_fragment ),
            Movie("Action, Adventure, Fantasy","74 REVIEWS", "Luxury Girl", "120 MIN",R.drawable.movie_wonder_woman_icon_fragment )
        )


    }

}