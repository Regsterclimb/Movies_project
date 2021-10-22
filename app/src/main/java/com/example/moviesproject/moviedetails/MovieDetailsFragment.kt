package com.example.moviesproject.moviedetails

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import com.example.moviesproject.data.Actor
import com.example.moviesproject.data.Movie
import com.example.moviesproject.hardcodedatalist.RepositoryProvider

class AvengersDownFragment : Fragment() {

    private var recycler: RecyclerView? = null

    private val viewModel: MovieDetailsViewModel by viewModels {
        MovieDetailsViewModelFactory(
            (requireActivity() as RepositoryProvider).provideMovieRepository()
        )
    }
    private var listner: ClickOnBackButton? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ClickOnBackButton) {
            listner = context
        }
    }

    companion object {

        private const val ARG_MOVIE_ID = "ARG_MOVIE_ID"

        fun newInstance(movieId: Int): AvengersDownFragment {
            val fragment = AvengersDownFragment()
            fragment.arguments = bundleOf(ARG_MOVIE_ID to movieId)
            return fragment
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
            this.adapter = ActorAdapter()
        }


        viewModel.loadMovieDetail(requireArguments().getInt(ARG_MOVIE_ID))

        viewModel.movieDetails.observe(this.viewLifecycleOwner, { bindUi(it, view) })

        setUpListners(view)

    }

    override fun onDetach() {
        super.onDetach()
        recycler = null
        listner = null
    }


    fun setListner(l: ClickOnBackButton) {
        listner = l
    }


    private fun bindActorsToActorsAdapter(adapter: ActorAdapter, actorslist: List<Actor>?) {
        if (actorslist != null) {
            adapter.bindActors(actorslist)
            Log.d("listactor1", "${actorslist}")

        }
    }


    private fun getMovieInfo(movie: Movie) {
        view?.let {
            Glide.with(it)
                .load(movie.detailImageUrl)
                .into(it.findViewById(R.id.MainImageAvengers))
        }

        view?.findViewById<TextView>(R.id.movieTitle)?.text = movie.title
        view?.findViewById<TextView>(R.id.tag_num)?.text = movie.pgAge.toString()
        view?.findViewById<TextView>(R.id.tags)?.text =
            movie.genres.joinToString(", ", "", "") { it.name }
        view?.findViewById<TextView>(R.id.reviews)?.text = movie.reviewCount.toString()
        view?.findViewById<TextView>(R.id.textView6)?.text = movie.storyLine
        val stars = listOf<ImageView?>(
            view?.findViewById(R.id.star1),
            view?.findViewById(R.id.star2),
            view?.findViewById(R.id.star3),
            view?.findViewById(R.id.star4),
            view?.findViewById(R.id.star5)
        )
        stars.forEachIndexed { index, imageView ->
            if (index < movie.rating) {
                imageView?.setImageResource(R.drawable.ic_star_icon)

            } else {
                imageView?.setImageResource(R.drawable.star_icon_gray)
            }

        }
    }


    private fun bindUi(movie: Movie, view: View) {
        getMovieInfo(movie)
        setUpListners(view)

        val adapter = view.findViewById<RecyclerView>(R.id.recycler_actor).adapter as ActorAdapter
        bindActorsToActorsAdapter(adapter, movie.actors)


    }

    private fun setUpListners(view: View) {
        view.findViewById<Button>(R.id.back_arrow)
            .apply {
                setOnClickListener {
                    listner?.backToMovieList() }
            }
    }

}

interface ClickOnBackButton {
    fun backToMovieList()
}