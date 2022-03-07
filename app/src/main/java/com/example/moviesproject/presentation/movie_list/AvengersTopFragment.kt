package com.example.moviesproject.presentation.movie_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviesproject.R
import com.example.moviesproject.databinding.MovieListFragmentBinding
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult.Error
import com.example.moviesproject.domain.use_cases.MoviesListUseCase.ListResult.Success


class AvengersTopFragment : Fragment(R.layout.movie_list_fragment) {

    private val adapter get() = viewBinding.movieRecycler.adapter as MovieAdapter

    private val viewModel: MovieListViewModel by viewModels {
        MovieListViewModelFactory(appContext = requireContext().applicationContext)
    }

    private val viewBinding by viewBinding(MovieListFragmentBinding::bind)

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.movieRecycler.apply {
            this.adapter = MovieAdapter {
                findNavController().navigate(R.id.action_avengersTopFragment_to_movieDetailsFragment, bundleOf(MOVIE_ID to it.id))
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
                is Success -> adapter.submitList(it.movieList)
                is Error -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        const val MOVIE_ID = "movieId"
    }
}
