package com.example.moviesproject.presentation.movie_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesproject.R
import com.example.moviesproject.domain.model.MovieDetails
import com.example.moviesproject.hardcodedatalist.RepositoryProvider

class AvengersDownFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(
            (requireActivity() as RepositoryProvider).provideMovieDetailsRepository()
        )
    }
    private var listner: Clicker? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is Clicker) {
            listner = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.avengers_fragment_fullscreen, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.findViewById<RecyclerView>(R.id.recycler_actor).apply {
            this.adapter = ActorAdapter { (listner?.moveToActorDetails()) }
        }

        viewModel.loadMovieDetail(requireArguments().getInt(ARG_MOVIE_ID))

        viewModel.movieDetails.observe(this.viewLifecycleOwner, { bindUi(it, view) })

    }

    override fun onDetach() {
        super.onDetach()
        listner = null
    }

    private fun getMovieInfo(movie: MovieDetails) {
        view?.let {
            Glide.with(it)
                .load(movie.posterImageUrlPath)
                .into(it.findViewById(R.id.main_actor_image_fragment))
        }

        view?.findViewById<TextView>(R.id.movieTitle)?.text = movie.title
        view?.findViewById<TextView>(R.id.tag_num)?.text = movie.pgAge
        view?.findViewById<TextView>(R.id.tags)?.text =
            movie.genres.joinToString(", ", "", "") { it.name }
        view?.findViewById<TextView>(R.id.reviews)?.text = movie.voteCount.toString()
        view?.findViewById<TextView>(R.id.textView6)?.text = movie.overview

        val stars = listOf<ImageView?>(
            view?.findViewById(R.id.star1),
            view?.findViewById(R.id.star2),
            view?.findViewById(R.id.star3),
            view?.findViewById(R.id.star4),
            view?.findViewById(R.id.star5)
        )
        stars.forEachIndexed { index, imageView ->
            if (index <= movie.voteAverage) {
                imageView?.setImageResource(R.drawable.ic_star_icon)

            } else {
                imageView?.setImageResource(R.drawable.star_icon_gray)
            }

        }
    }

    private fun bindUi(movie: MovieDetails, view: View) {
        getMovieInfo(movie)
        setUpListners(view)

        val adapter = view.findViewById<RecyclerView>(R.id.recycler_actor).adapter as ActorAdapter
        adapter.submitList(movie.actorList)

    }

    fun setListner(l: Clicker) { // ClickListner Setter
        listner = l
    }

    private fun setUpListners(view: View) {
        view.findViewById<Button>(R.id.back_arrow)
            .apply {
                setOnClickListener {
                    listner?.backToMovieList()
                }
            }
    }

    companion object {
        private const val ARG_MOVIE_ID = "ARG_MOVIE_ID"

        fun newInstance(movieId: Int): AvengersDownFragment { // Fragment Instance with movieID
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