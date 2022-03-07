package com.example.moviesproject.presentation.actor_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.moviesproject.R
import com.example.moviesproject.databinding.ActorDetailsFragmentBinding

class ActorDetailsFragment : Fragment(R.layout.actor_details_fragment) {

    private val viewBinding by viewBinding(ActorDetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}