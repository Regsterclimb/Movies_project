package com.example.moviesproject.presentation.actor_details

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.moviesproject.R

class ActorDetailsFragment : Fragment() {

    companion object

    fun newInstance(): ActorDetailsFragment = ActorDetailsFragment()

    private var listner: ActorDetailsClicker? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ActorDetailsClicker) {
            listner = context
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.actor_details_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews(view)
        view.findViewById<Button>(R.id.back_arrow).setOnClickListener { listner?.moveToBackStack() }
    }


    override fun onDetach() {
        listner = null
        super.onDetach()
    }


    private fun setViews(view: View) {
        view.findViewById<ImageView>(R.id.main_actor_image_fragment)
        view.findViewById<TextView>(R.id.actor_name_fragment)
        view.findViewById<TextView>(R.id.actor_birthday_fragment)
        view.findViewById<TextView>(R.id.actor_birthday_place_fragment)
        view.findViewById<TextView>(R.id.actor_genres_fragment)
        view.findViewById<TextView>(R.id.actor_biography_title_fragment)
        view.findViewById<TextView>(R.id.actor_biography_fragment)

    }

    fun setListner(l: ActorDetailsClicker) {
        listner = l
    }


}

interface ActorDetailsClicker {
    fun moveToBackStack()
}