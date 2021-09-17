package com.example.moviesproject.avengers

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.moviesproject.R

class AvengersTopFragment: Fragment() {

    val buttonGoToAvengersFrag : Button? = null
    val listner : topMovieButtonClickerOnFragment? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

         return inflater.inflate(R.layout.top_movie_icon_fragment,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonGoToAvengersFrag?.apply { setOnClickListener { listner?.clickOnTopFragment() } }

    }
}
interface topMovieButtonClickerOnFragment {
    fun clickOnTopFragment()
}