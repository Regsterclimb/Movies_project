package com.example.moviesproject.presentation.movie_details

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.R
import com.example.moviesproject.databinding.AvengersFragmentFullscreenBinding
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult.Error
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult.Success
import com.example.moviesproject.presentation.movie_list.support.StarsColor

class AvengersDownFragment : Fragment(R.layout.avengers_fragment_fullscreen) {

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    private val viewBinding by viewBinding(AvengersFragmentFullscreenBinding::bind)

    private val adapter get() = viewBinding.recyclerActor.adapter as ActorAdapter

    private var listener: Clicker? = null

    override fun onAttach(context: Context) {
        if (context is Clicker) {
            listener = context
        }
        super.onAttach(context)
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.recyclerActor.apply {
            this.adapter = ActorAdapter {
                listener?.moveToActorDetails()
            }
        }
        viewBinding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.loadFreshMovieToLiveData(requireArguments().getInt(ARG_MOVIE_ID))
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner) {
            with(viewBinding) {
                viewGroup.isVisible = !it
                swipeRefreshLayout.isRefreshing = it
            }
        }
        viewModel.loadMovieDetails(requireArguments().getInt(ARG_MOVIE_ID))
        viewModel.mutableDetailsResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is Success -> bindUi(it.movieDetails)
                is Error -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    private fun getMovieInfo(movie: MovieDetails, starsColor: StarsColor) {
        with(viewBinding) {
            posterImage.load(movie.imageUrl)
            movieTitle.text = movie.title
            numTag.text = movie.pgAge
            tags.text = movie.genreResponses.joinToString(", ", "", "") {
                it.name
            }
            reviews.text = movie.reviewCount.toString()
            overView.text = movie.storyLine
            starsColor.setColor(
                listOf(
                    viewBinding.star1,
                    viewBinding.star2,
                    viewBinding.star3,
                    viewBinding.star4,
                    viewBinding.star5,
                ), movie.rating
            )
            backArrow.setOnClickListener {
                listener?.backToMovieList()
            }
        }
    }

    private fun bindUi(movie: MovieDetails) {
        getMovieInfo(movie, StarsColor.Base())
        adapter.submitList(movie.actorResponseList)
    }

    companion object {
        private const val ARG_MOVIE_ID = "ARG_MOVIE_ID"

        fun newInstance(movieId: Int): AvengersDownFragment {
            val fragment = AvengersDownFragment()
            fragment.arguments = bundleOf(ARG_MOVIE_ID to movieId)
            return fragment
        }
    }
}

interface Clicker {
    fun backToMovieList()
    fun moveToActorDetails()
}