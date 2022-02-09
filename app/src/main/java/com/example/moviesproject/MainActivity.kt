package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesproject.data.repository.*
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.ActorDetailsRepository
import com.example.moviesproject.domain.use_cases.MovieDetailsRepository
import com.example.moviesproject.hardcodedatalist.RepositoryProvider
import com.example.moviesproject.presentation.actor_details.ActorDetailsClicker
import com.example.moviesproject.presentation.actor_details.ActorDetailsFragment
import com.example.moviesproject.presentation.movie_details.AvengersDownFragment
import com.example.moviesproject.presentation.movie_details.Clicker
import com.example.moviesproject.presentation.movie_list.AvengersTopFragment
import com.example.moviesproject.presentation.movie_list.OnItemClickListner

class MainActivity : AppCompatActivity(), OnItemClickListner, Clicker,
    RepositoryProvider, ActorDetailsClicker {

    private val movieRepository =
        MovieRepositoryImpl(this, ParseMovie.Base(), MainDataRepository.Base())
    private val movieDetailsDataRepository = MovieDetailsDataRepositoryImpl(this)
    private val actorDetailsRepository = ActorDetailsRepositoryImpl(this)


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
                    .apply { setListner(this@MainActivity) }
            )
            .addToBackStack(null)
            .commit()
    }

    override fun backToMovieList() {
        supportFragmentManager.popBackStack()
    }

    override fun moveToActorDetails() {
        supportFragmentManager.beginTransaction()
            .add(R.id.container,
                ActorDetailsFragment().newInstance().apply { setListner(this@MainActivity) }
            )
            .addToBackStack(null)
            .commit()
    }

    override fun provideMovieRepository(): com.example.moviesproject.domain.use_cases.MovieRepository {
        return movieRepository
    }

    override fun provideMovieDetailsRepository(): MovieDetailsRepository {
        return movieDetailsDataRepository
    }

    override fun provideActorDetailsRepository(): ActorDetailsRepository {
        TODO()
    }

    private fun startMovieListFragment() {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.container,
                AvengersTopFragment.newInstance()
                    .apply {
                        setListner(this@MainActivity)
                    })
            commit()
        }
    }

    override fun moveToBackStack() {
        supportFragmentManager.popBackStack()
    }


}