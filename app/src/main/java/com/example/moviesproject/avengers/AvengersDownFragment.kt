package com.example.moviesproject.avengers

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.moviesproject.R
import com.example.moviesproject.data.Actor
import com.example.moviesproject.data.Movie

class AvengersDownFragment : Fragment(), Clicker {

    private var topimage: ImageView? = null
    private var titletext: TextView? = null
    private var tagnum: TextView? = null
    private var genrestags: TextView? = null
    private var reviews: TextView? = null
    private var storyline: TextView? = null
    private var recycler: RecyclerView? = null
    private var stars: List<ImageView>? = null

    private var adapter1 = ActorAdapter()

    private var buttonOnBack: Button? = null

    private var listner: Clicker? = null

    private var movieDownFragment: Movie? = null

    companion object {
        fun newInstance(keyString: String, movie: Movie): AvengersDownFragment {
            val args = Bundle()
            args.putString(keyString, "AvengersDownFragment")
            val fragment = AvengersDownFragment()
            fragment.getMovieInfo(movie)
            fragment.arguments = args
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

        initViews(view)
        setMovieInfo(view)
        recycler = view.findViewById(R.id.recycler_actor)
        recycler?.adapter = adapter1
        buttonOnBack = view.findViewById<Button>(R.id.back_arrow)
            .apply { setOnClickListener { backToMovieList() } }
        Log.d("listactor3", "${adapter1.getActors()}")

    }

    // TODO check, all views must be null
    override fun onDestroyView() {
        super.onDestroyView()

        topimage = null
        titletext = null
        tagnum = null
        genrestags = null
        reviews = null
        storyline = null
        recycler = null

    }

    private fun updateData() {
        TODO("if needed")

    }


    fun setListner(l: Clicker) {
        listner = l
    }


    fun setMovieInfo(view: View) {
        if (movieDownFragment != null) {
            if (topimage != null) {
                Glide.with(view)
                    .load(movieDownFragment?.detailImageUrl)
                    .into(topimage!!)
            }
            titletext?.text = movieDownFragment?.title
            tagnum?.text = movieDownFragment?.pgAge.toString()
            genrestags?.text = movieDownFragment?.genres?.joinToString(", ", "", "") { it.name }
            reviews?.text = movieDownFragment?.reviewCount.toString()
            storyline?.text = movieDownFragment?.storyLine


            bindActorsToActorsAdapter(adapter1, movieDownFragment?.actors)

            Log.d("listactor", "${movieDownFragment?.actors.toString()}")


        }
    }

    fun bindActorsToActorsAdapter(adapter: ActorAdapter, actorslist: List<Actor>?) {
        if (actorslist != null) {
            adapter.bindActors(actorslist)
            Log.d("listactor1", "${actorslist}")

        }
    }


    fun getMovieInfo(movie: Movie) {
        movieDownFragment = movie
    }

    fun initViews(view: View) {
        topimage = view.findViewById(R.id.MainImageAvengers)
        titletext = view.findViewById(R.id.movieTitle)
        tagnum = view.findViewById(R.id.tag_num)
        genrestags = view.findViewById(R.id.tags)
        reviews = view.findViewById(R.id.reviews)
        storyline = view.findViewById(R.id.textView6)
        stars = listOf(
            view.findViewById(R.id.star1),
            view.findViewById(R.id.star2),
            view.findViewById(R.id.star3),
            view.findViewById(R.id.star4),
            view.findViewById(R.id.star5))
    }

    override fun backToMovieList() {
        val activity = view?.context as AppCompatActivity
        activity.supportFragmentManager.popBackStack()
    }
}

interface Clicker {
    fun backToMovieList()
}