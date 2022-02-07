package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.repository.ActorDetailsRepository
import com.example.moviesproject.domain.repository.MovieDetailsDataRepository
import com.example.moviesproject.domain.repository.MovieRepository
import com.example.moviesproject.domain.use_cases.GetActorDetailsRepository
import com.example.moviesproject.domain.use_cases.GetMovieDetailsRepository
import com.example.moviesproject.domain.use_cases.GetMovieRepository
import com.example.moviesproject.hardcodedatalist.RepositoryProvider
import com.example.moviesproject.presentation.actor_details.ActorDetailsClicker
import com.example.moviesproject.presentation.actor_details.ActorDetailsFragment
import com.example.moviesproject.presentation.movie_details.AvengersDownFragment
import com.example.moviesproject.presentation.movie_details.Clicker
import com.example.moviesproject.presentation.movie_list.AvengersTopFragment
import com.example.moviesproject.presentation.movie_list.OnItemClickListner

class MainActivity : AppCompatActivity(), OnItemClickListner, Clicker,
    RepositoryProvider, ActorDetailsClicker {

    private val movieRepository = MovieRepository(this)
    private val movieDetailsDataRepository = MovieDetailsDataRepository(this)
    private val actorDetailsRepository = ActorDetailsRepository(this)


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

    override fun provideMovieRepository(): GetMovieRepository {
        return movieRepository
    }

    override fun provideMovieDetailsRepository(): GetMovieDetailsRepository {
        return movieDetailsDataRepository
    }

    override fun provideActorDetailsRepository(): GetActorDetailsRepository {
        TODO("Not yet implemented")
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
    override fun moveToBackStack() {
        supportFragmentManager.popBackStack()
    }


}