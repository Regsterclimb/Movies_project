package com.example.moviesproject.presentation.movie_details

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.R
import com.example.moviesproject.databinding.AvengersFragmentFullscreenBinding
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult.Error
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase.DetailsResult.Success
import com.example.moviesproject.presentation.movie_list.AvengersTopFragment
import com.example.moviesproject.presentation.support.StarsColor

class MovieDetailsFragment : Fragment(R.layout.avengers_fragment_fullscreen) {

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    private val viewBinding by viewBinding(AvengersFragmentFullscreenBinding::bind)

    private val adapter get() = viewBinding.recyclerActor.adapter as ActorAdapter

    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val movieId = requireArguments().getInt(AvengersTopFragment.MOVIE_ID)

        viewBinding.recyclerActor.apply {
            this.adapter = ActorAdapter {
                findNavController().navigate(R.id.action_movieDetailsFragment_to_actorDetailsFragment)
            }
        }
        viewBinding.swipeRefreshLayout.apply {
            setOnRefreshListener {
                viewModel.loadFreshMovieToLiveData(movieId)
                adapter.notifyDataSetChanged()
            }
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner) {
            with(viewBinding) {
                viewGroup.isVisible = !it
                swipeRefreshLayout.isRefreshing = it
            }
        }
        viewModel.loadMovieDetails(movieId)
        viewModel.mutableDetailsResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is Success -> bindUi(it.movieDetails)
                is Error -> Toast.makeText(requireContext(), it.error, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getMovieInfo(movie: MovieDetails, starsColor: StarsColor) =
        with(viewBinding) {
            posterImage.load(movie.imageUrl)
            movieTitle.text = movie.title
            numTag.text = movie.pgAge
            tags.text = movie.genreResponses
            reviews.text = movie.reviewCount
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
                findNavController().popBackStack()
            }
        }

    private fun bindUi(movie: MovieDetails) {
        getMovieInfo(movie, StarsColor.Base())
        adapter.submitList(movie.actorResponseList)
    }
}