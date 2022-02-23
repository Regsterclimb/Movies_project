package com.example.moviesproject.presentation.actor_details

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviesproject.R
import com.example.moviesproject.databinding.ActorDetailsFragmentBinding

class ActorDetailsFragment : Fragment(R.layout.actor_details_fragment) {

    fun newInstance(): ActorDetailsFragment = ActorDetailsFragment()

    private val viewBinding by viewBinding(ActorDetailsFragmentBinding::bind)

    private var listner: ActorDetailsClicker? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)

        if (context is ActorDetailsClicker) {
            listner = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backArrow.setOnClickListener {
            listner?.moveToBackStack()
        }
    }

    override fun onDetach() {
        listner = null
        super.onDetach()
    }
}

interface ActorDetailsClicker {
    fun moveToBackStack()
}