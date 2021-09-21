package com.example.moviesproject.avengers

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moviesproject.R

class AvengersTopFragment: Fragment() {

    private var imageGoToAvengersFrag : ImageButton? = null

    private var listner : topMovieButtonClickerOnFragment? = null

    companion object {

        fun newInstance(fragmentKeyString : String) :AvengersTopFragment {
            val args = Bundle()
            args.putString(fragmentKeyString, "avengersTopFragment")
            val fragment = AvengersTopFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.top_movie_icon_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view?.findViewById<ImageButton>(R.id.avengers_icon)
            ?.apply {
                setOnClickListener {
                    listner?.clickOnTopFragment() } }



    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is topMovieButtonClickerOnFragment ){
            listner = context
        }
    }

    override fun onDetach() {
        super.onDetach()
        listner = null
    }

    fun setListener(l: topMovieButtonClickerOnFragment) {
        listner  = l
    }

    interface topMovieButtonClickerOnFragment {
        fun clickOnTopFragment()
    }

}
