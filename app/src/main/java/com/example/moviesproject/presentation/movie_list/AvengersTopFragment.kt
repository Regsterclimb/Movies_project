package com.example.moviesproject.presentation.movie_list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.moviesproject.R
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.hardcodedatalist.RepositoryProvider


class AvengersTopFragment : Fragment() {

    private val viewModel: MovieListViewModel by viewModels { MovieListViewModelFactory((requireActivity() as RepositoryProvider).provideMovieRepository()) }

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

        view.findViewById<RecyclerView>(R.id.movie_recycler).apply {
            this.adapter = MovieAdapter {
                listner?.clickOnMovieCart(it)
            }
        }

        viewModel.loadMovieToLiveData()
        viewModel.liveDatamovieList.observe(this.viewLifecycleOwner, { loadMovieDataToAdapter(it) })

    }

    override fun onDetach() {
        super.onDetach()
        listner = null

    }


    private fun loadMovieDataToAdapter(list: List<Movie>) {
        val adapter = view?.findViewById<RecyclerView>(R.id.movie_recycler)?.adapter as MovieAdapter
        adapter.submitList(list)
    }


    fun setListner(clicker: OnItemClickListner?) {
        listner = clicker
    }

    companion object {
        fun newInstance(): AvengersTopFragment = AvengersTopFragment()
    }

}

interface OnItemClickListner {
    fun clickOnMovieCart(movie: Movie)
}
