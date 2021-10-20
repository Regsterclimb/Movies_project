package com.example.moviesproject.avengers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.android.academy.fundamentals.homework.data.JsonMovieRepository
import com.example.moviesproject.R
import com.example.moviesproject.data.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch


class AvengersTopFragment: Fragment() {

    private var recycler: RecyclerView? = null

    private var movieList : List<Movie> = listOf()

    private var coroutineScope = CoroutineScope(Dispatchers.Default + SupervisorJob())


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

        val adapter = MovieAdapter{
            clickMaFu.clickOnTopFragment(it)
        }


        recycler?.adapter = adapter

        loadDataToAdapter(adapter)



    }
    override fun onStart() {
        super.onStart()

    }

    private fun loadDataToAdapter(adapter: MovieAdapter) {
        coroutineScope.launch {
            val moviesData = JsonMovieRepository(requireContext()).loadMovies()
            adapter.bindMovies(moviesData)
            Log.d("moviesData", " $moviesData")
        }
    }

    private fun setListToAdapter(adapter: MovieAdapter) {
        adapter.bindMovies(movieList)

    }

    private fun updateData() {


    }

    private fun doOnClick(movie: Movie) {
        val activity = view?.context as AppCompatActivity
        activity.supportFragmentManager.beginTransaction()
            .add(R.id.avengers_container_for_down_fragment, AvengersDownFragment.newInstance("AvengersDownFragment",movie))
            .addToBackStack(null)
            .commit()
    }

    private val clickMaFu = object : MovieAdapter.clickOnMovie {

        override fun clickOnTopFragment(movie: Movie) {
            doOnClick(movie)
        }

    }


}
