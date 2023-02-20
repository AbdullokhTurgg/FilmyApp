package com.example.movieappazi.ui.person.actors_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.ResponseState
import com.example.movieappazi.app.models.person.PersonUi
import com.example.movieappazi.app.utils.extensions.launchWhenViewStarted
import com.example.movieappazi.app.utils.extensions.makeToast
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.showView
import com.example.movieappazi.databinding.FragmentActorsBinding
import com.example.movieappazi.databinding.FragmentPersonBinding
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.adapters.person.PersonAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class PersonFragment : BaseFragment<FragmentPersonBinding, ActorsFragmentViewModel>(
    FragmentPersonBinding::inflate
), RvClickListener<PersonUi> {
    override val viewModel by viewModels<ActorsFragmentViewModel>()

    private val personsAdapter by lazy { PersonAdapter(this@PersonFragment) }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupUiAndClickers()
        requireBinding().personsRv.adapter = personsAdapter
        observe()
    }

    private fun observe() = with(viewModel) {
        error.onEach {
            makeToast(it, requireContext())
        }
        launchWhenViewStarted {
            persons.observe { personsAdapter.personsList = it.persons }
            requireBinding().pageConstraint.visibility = View.VISIBLE
            requireBinding().isEmptyLoading.visibility = View.GONE
            personResponseState.observe(::observeResponseState)
        }
    }


    private fun observeResponseState(state: ResponseState) = with(requireBinding()) {
        prevPageText.text = state.previousPage.toString()
        currentPageText.text = state.page.toString()
        nextPageText.text = state.nextPage.toString()
        prevBtnPerson.apply {
            isClickable = state.isHasPreviousPage
            isFocusable = state.isHasPreviousPage
        }
        nextBtnPerson.apply {
            isClickable = state.isHasNextPage
            isFocusable = state.isHasNextPage
        }
    }

    private fun setupUiAndClickers() {
        requireBinding().apply {
            nextBtnPerson.setOnDownEffectClickListener {
                viewModel.nextPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
            prevBtnPerson.setOnDownEffectClickListener {
                viewModel.previousPage()
                mainScrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }
    }

    override fun onItemClick(item: PersonUi) = viewModel.goPersonDetails(item)

    override fun onLongClick(item: PersonUi) = makeToast(item.name, requireContext())

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}