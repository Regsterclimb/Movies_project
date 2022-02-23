package com.example.moviesproject.presentation.movie_list

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviesproject.R
import com.example.moviesproject.databinding.MovieListFragmentBinding
import com.example.moviesproject.domain.model.Movie
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult.Error
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult.Success


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

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.movieRecycler.apply {
            this.adapter = MovieAdapter {
                listener?.clickOnMovieCart(it)
            }
        }
        viewBinding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.loadFreshMovieToLiveData()
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner) {
            with(viewBinding) {
                movieRecycler.isVisible = !it
                swipeRefreshLayout.isRefreshing = it
            }
        }
        viewModel.mutableListResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is Success -> {
                    adapter.submitList(it.movieList)
                }
                is Error -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
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
