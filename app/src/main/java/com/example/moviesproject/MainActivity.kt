package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.academy.fundamentals.homework.data.JsonMovieRepository
import com.android.academy.fundamentals.homework.data.MovieRepository
import com.example.moviesproject.data.moviedata.Movie
import com.example.moviesproject.hardcodedatalist.RepositoryProvider
import com.example.moviesproject.moviedetails.AvengersDownFragment
import com.example.moviesproject.moviedetails.ClickOnBackButton
import com.example.moviesproject.movielist.AvengersTopFragment
import com.example.moviesproject.movielist.OnItemClickListner

class MainActivity : AppCompatActivity(), OnItemClickListner, ClickOnBackButton,
    RepositoryProvider {

    private val jsonMovieRepository = JsonMovieRepository(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            startMovieListFragment()
        }
    }


    override fun clickOnMovieCart(movie: Movie) {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.avengers_container_for_down_fragment,
                AvengersDownFragment.newInstance(movie.id)
                    .apply { setListner(this@MainActivity) }
            )
            .addToBackStack(null)
            .commit()
    }

    override fun backToMovieList() {
        supportFragmentManager.popBackStack()
    }

    override fun provideMovieRepository(): MovieRepository {
        return jsonMovieRepository
    }


    fun startMovieListFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container,
                AvengersTopFragment.newInstance()
                    .apply {
                        setListner(this@MainActivity)
                    })
            commit()
        }

    }


}