package com.example.moviesproject.avengers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.data.Movie
import com.example.moviesproject.hardcodedatalist.MoviesDataSource
import com.google.android.material.snackbar.Snackbar

class AvengersTopFragment: Fragment() {


    private var recycler: RecyclerView? = null


    companion object {

        fun newInstance(fragmentKeyString : String) :AvengersTopFragment {
            val args = Bundle()
            args.putString(fragmentKeyString, "avengersTopFragment")
            val fragment = AvengersTopFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.top_movie_icon_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movie_recycler)
        recycler?.adapter = MovieAdapter(clickMaFu)


    }
    override fun onStart() {
        super.onStart()

        updateData()
    }

    private fun updateData() {
        (recycler?.adapter as? MovieAdapter)?.apply {
            bindMovies(MoviesDataSource().getMovies())
        }
    }
    private fun doOnClick(movie: Movie) {
        recycler?.let { rv ->
            Snackbar.make(
                rv,
                getString(R.string.fragment_actors_chosen_text, movie.movieTitle),
                Snackbar.LENGTH_SHORT)
                .show()
        }
    }

    private val clickMaFu = object : MovieAdapter.clickOnMovie {

        override fun clickOnTopFragment(movie: Movie) {
            doOnClick(movie)
        }

    }


}
