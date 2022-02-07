package com.example.moviesproject.presentation.movie_list

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviesproject.R
import com.example.moviesproject.databinding.MovieListFragmentBinding
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.hardcodedatalist.RepositoryProvider


class AvengersTopFragment : Fragment(R.layout.movie_list_fragment) {

    private val viewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory((requireActivity() as RepositoryProvider).provideMovieRepository())
    }

    private val viewBinding by viewBinding(MovieListFragmentBinding::bind)

    private var listener: OnItemClickListner? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClickListner) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.movie_recycler).apply {
            this.adapter = MovieAdapter {
                listener?.clickOnMovieCart(it)
            }
        }
        viewModel.loadMovieToLiveData()
        viewModel.liveDataMovieList.observe(this.viewLifecycleOwner) {
            val adapter = view.findViewById<RecyclerView>(R.id.movie_recycler)?.adapter as MovieAdapter
            adapter.submitList(it)
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }
    fun setListner(clicker: OnItemClickListner?) {
        listener = clicker
    }

    companion object {
        fun newInstance(): AvengersTopFragment = AvengersTopFragment()
    }
}

interface OnItemClickListner {
    fun clickOnMovieCart(movie: Movie)
}
