package com.example.moviesproject.movielist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.data.Movie
import com.example.moviesproject.hardcodedatalist.RepositoryProvider


class AvengersTopFragment : Fragment() {

    private val viewModel : MovieListViewModel by viewModels{MovieListViewModelFactory((requireActivity() as RepositoryProvider).provideMovieRepository())}

    private var recycler: RecyclerView? = null

    private var listner: OnItemClickListner? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is OnItemClickListner) {
            listner = context
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.top_movie_icon_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycler = view.findViewById(R.id.movie_recycler)

        viewModel.loadMovieToLiveData()

        val adapter = MovieAdapter{
            listner?.clickOnMovieCart(it)
        }
        viewModel.liveDatamovieList.observe(this.viewLifecycleOwner,{loadDataToAdapter(adapter, it)})
        recycler?.adapter = adapter

    }

    override fun onDetach() {
        super.onDetach()
        listner = null

    }

    private fun loadDataToAdapter(adapter: MovieAdapter,list : List<Movie>) {
            adapter.bindMovies(list)
    }

    fun setListner(clicker : OnItemClickListner?) {
        listner = clicker
    }
    companion object {
        fun newInstance(): AvengersTopFragment = AvengersTopFragment()
    }


}

interface OnItemClickListner {
    fun clickOnMovieCart(movie: Movie)
}
