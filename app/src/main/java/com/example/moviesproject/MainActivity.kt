package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.presentation.actor_details.ActorDetailsClicker
import com.example.moviesproject.presentation.actor_details.ActorDetailsFragment
import com.example.moviesproject.presentation.movie_details.AvengersDownFragment
import com.example.moviesproject.presentation.movie_details.Clicker
import com.example.moviesproject.presentation.movie_list.AvengersTopFragment
import com.example.moviesproject.presentation.movie_list.OnItemClickListener

class MainActivity : AppCompatActivity(), OnItemClickListener, Clicker, ActorDetailsClicker {

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
                R.id.container,
                AvengersDownFragment.newInstance(movie.id)
            )
            .addToBackStack(null)
            .commit()
    }

    override fun backToMovieList() {
        supportFragmentManager.popBackStack()
    }

    override fun moveToActorDetails() {
        supportFragmentManager.beginTransaction()
            .add(
                R.id.container,
                ActorDetailsFragment().newInstance()
            )
            .addToBackStack(null)
            .commit()
    }

    private fun startMovieListFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(
                R.id.container,
                AvengersTopFragment.newInstance()
            )
            commit()
        }
    }

    override fun moveToBackStack() {
        supportFragmentManager.popBackStack()
    }
}