package com.example.movieappazi.ui.actors_details_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.network.retrofit.utils.Utils
import com.example.domain.state.DataRequestState
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentActorsDetailsBinding
import com.example.movieappazi.extensions.hideView
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ActorsDetailsFragment :
    BaseFragment<FragmentActorsDetailsBinding, ActorsDetailsFragmentViewModel>(
        FragmentActorsDetailsBinding::inflate) {

    private val movieId: Int by lazy {
        ActorsDetailsFragmentArgs.fromBundle(requireArguments()).person.id
    }

    @Inject
    lateinit var viewModelFactory: ActorsDetailsFragmentViewModelFactory.Factory

    override val viewModel by viewModels<ActorsDetailsFragmentViewModel> {
        viewModelFactory.create(movieId = movieId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
        observePersonDetails()
    }


    private fun observePersonDetails() {
        lifecycleScope.launchWhenStarted {
            viewModel.personFlow.collectLatest {
                when (it) {
                    is DataRequestState.Success -> {
                        observePersonUi(it.data)
                    }
                    is DataRequestState.Error -> makeToast(it.error.message.toString(),
                        requireContext())
                }
            }
        }
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }
    }

    private fun observePersonUi(person: PersonDetailsUi) = with(requireBinding()) {
        Picasso.get().load(Utils.POSTER_PATH_URL + person.profile_path).into(personImage)
        namesText.text = person.name
        birthday.text = person.birthday
        profession.text = person.known_for_department
        biography.text = person.biography
        birthday.text = person.birthday
        deathDay.text = person.deathDay
        personGender.text = person.gender.toString()
        personName.text = person.name
        personPopularity.text = person.popularity.toFloat().toString()
        birthPlace.text = person.place_of_birth
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}