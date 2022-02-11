package com.example.moviesproject

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.moviesproject.data.remote.NetworkModuleImpl
import com.example.moviesproject.data.repository.ActorDetailsRepositoryImpl
import com.example.moviesproject.data.repository.MovieDetailsDataRepositoryImpl
import com.example.moviesproject.data.repository.movie_details.MainMovieDetailsRepository
import com.example.moviesproject.data.repository.movie_details.ParseMovieDetails
import com.example.moviesproject.data.repository.movie_list.MainMoviesRepository
import com.example.moviesproject.data.repository.movie_list.MovieRepositoryImpl
import com.example.moviesproject.data.repository.movie_list.ParseMovie
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

    private val movieRepository = MovieRepositoryImpl(
        this, ParseMovie.Base(), MainMoviesRepository.Base(), NetworkModuleImpl()
    )

    private val movieDetailsDataRepository = MovieDetailsDataRepositoryImpl(
        this, MainMoviesRepository.Base(), ParseMovieDetails.Base(), NetworkModuleImpl(),
        MainMovieDetailsRepository.Base(MainMoviesRepository.Base())
    )

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