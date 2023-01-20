package com.example.movieappazi.ui.actors_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentActorsBinding
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.zAdapter.person.PersonAdapter
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import com.example.movieappazi.uiModels.person.PersonUi
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest


@AndroidEntryPoint
class ActorsFragment : Fragment(), RvClickListener<PersonUi> {
    private val binding by lazy {
        FragmentActorsBinding.inflate(layoutInflater)
    }
    private val viewModel: ActorsFragmentViewModel by viewModels()
    private val personAdapter: PersonAdapter by lazy {
        PersonAdapter(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observePersons()
    }

    private fun setupRecyclerView() {
        binding.personRv.adapter = personAdapter
    }

    private fun observePersons() {
        lifecycleScope.launchWhenResumed {
            viewModel.persons.collectLatest {
                personAdapter.personsList = it.persons
            }
        }
    }

    override fun onItemClick(item: PersonUi) {

    }

    override fun onLongClick(item: PersonUi) {

    }


}