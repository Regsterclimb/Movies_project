package com.example.moviesproject.presentation.movie_details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.R
import com.example.moviesproject.databinding.AvengersFragmentFullscreenBinding
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.domain.use_cases.MovieDetailsUseCase
import com.example.moviesproject.presentation.movie_list.support.StarsColor

class AvengersDownFragment : Fragment(R.layout.avengers_fragment_fullscreen) {

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(applicationContext = requireContext().applicationContext)
    }

    private val viewBinding by viewBinding(AvengersFragmentFullscreenBinding::bind)

    private var listner: Clicker? = null

    override fun onAttach(context: Context) {
        if (context is Clicker) {
            listner = context
        }
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewBinding.recyclerActor.apply {
            this.adapter = ActorAdapter {
                listner?.moveToActorDetails()
            }
        }
        viewModel.isLoading.observe(this.viewLifecycleOwner) {
            
        }
        viewModel.loadMovieDetails(requireArguments().getInt(ARG_MOVIE_ID))
        viewModel.mutableDetailsResult.observe(this.viewLifecycleOwner) {
            when (it) {
                is MovieDetailsUseCase.DetailsResult.Success -> {
                    bindUi(it.movieDetails, view)
                }
                is MovieDetailsUseCase.DetailsResult.Error -> {}
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        listner = null
    }

    private fun getMovieInfo(movie: MovieDetails, starsColor: StarsColor) {
        with(viewBinding) {
            posterImage.load(movie.imageUrl)
            movieTitle.text = movie.title
            numTag.text = movie.pgAge
            tags.text = movie.genreResponses.joinToString(", ", "", "") { it.name }
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
                listner?.backToMovieList()
            }
        }
    }

    private fun bindUi(movie: MovieDetails, view: View) {
        getMovieInfo(movie, StarsColor.Base())
        val adapter = view.findViewById<RecyclerView>(R.id.recyclerActor).adapter as ActorAdapter
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