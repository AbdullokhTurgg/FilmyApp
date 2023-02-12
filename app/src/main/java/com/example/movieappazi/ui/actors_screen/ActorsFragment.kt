package com.example.movieappazi.ui.actors_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentActorsBinding
import com.example.movieappazi.extensions.launchWhenViewStarted
import com.example.movieappazi.extensions.showView
import com.example.movieappazi.ui.adapters.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.adapters.person.PersonAdapter
import com.example.movieappazi.uiModels.person.PersonUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class ActorsFragment :
    BaseFragment<FragmentActorsBinding, ActorsFragmentViewModel>(FragmentActorsBinding::inflate),
    RvClickListener<PersonUi> {

    override val viewModel: ActorsFragmentViewModel by viewModels()

    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter(this)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observePersons()
    }

    private fun setupRecyclerView() = with(requireBinding()) {
        personRv.adapter = personAdapter
    }


    private fun observePersons() = with(viewModel) {
        launchWhenViewStarted {
            persons.observe {
                personAdapter.personsList = it.persons
                visibilities()
            }
        }
    }

    private fun visibilities() = with(requireBinding()) {
        personRv.visibility = View.VISIBLE
        shimmerLayoutForPerson.visibility = View.INVISIBLE
    }

    override fun onLongClick(item: PersonUi) {}

    override fun onItemClick(item: PersonUi) {
        viewModel.goPersonDetails(person = item)
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}