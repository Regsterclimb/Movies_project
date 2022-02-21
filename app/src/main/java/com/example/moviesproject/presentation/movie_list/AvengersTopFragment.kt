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


class AvengersTopFragment : Fragment(R.layout.movie_list_fragment) {
    private val adapter get() = viewBinding.movieRecycler.adapter as MovieAdapter

    private val viewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(appContext = requireContext().applicationContext)
    }

    private val viewBinding by viewBinding(MovieListFragmentBinding::bind)

    private var listener: OnItemClickListener? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnItemClickListener) {
            listener = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.findViewById<RecyclerView>(R.id.movieRecycler).apply {
            this.adapter = MovieAdapter {
                listener?.clickOnMovieCart(it)
            }
        }
        viewModel.liveDataMovieList.observe(this.viewLifecycleOwner) {
            (adapter).submitList(it)
        }
    }

    override fun onDetach() {
        listener = null
        super.onDetach()
    }

    companion object {
        fun newInstance(): AvengersTopFragment = AvengersTopFragment()
    }
}

interface OnItemClickListener {
    fun clickOnMovieCart(movie: Movie)
}
