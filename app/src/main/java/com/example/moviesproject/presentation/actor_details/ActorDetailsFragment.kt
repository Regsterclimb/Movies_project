package com.example.moviesproject.presentation.actor_details

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import coil.load
import com.example.moviesproject.R
import com.example.moviesproject.databinding.ActorDetailsFragmentBinding
import com.example.moviesproject.presentation.movie_details.MovieDetailsFragment

class ActorDetailsFragment : Fragment(R.layout.actor_details_fragment) {

    private val viewModel: ActorDetailsViewModel by viewModels {
        ActorDetailsViewModelFactory(
            appContext = requireContext().applicationContext
        )
    }

    private val viewBinding by viewBinding(ActorDetailsFragmentBinding::bind)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.loadActorDetails(requireArguments().getInt(MovieDetailsFragment.ACTOR_ID)) //TODO()
        viewModel.actorDetails.observe(this.viewLifecycleOwner) {
            with(viewBinding) {
                actorNameFragment.text = it.name
                actorBirthdayFragment.text = it.birthday
                actorBirthdayPlaceFragment.text = it.placeOfBirth
                actorBiographyFragment.text = it.biography
                posterImage.load(it.posterUrlPath)
            }
        }

        viewBinding.backArrow.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}