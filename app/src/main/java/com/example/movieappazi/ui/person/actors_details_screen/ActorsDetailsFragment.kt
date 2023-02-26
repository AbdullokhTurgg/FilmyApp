package com.example.movieappazi.ui.person.actors_details_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.person.PersonDetailsUi
import com.example.movieappazi.app.models.person.PersonUi
import com.example.movieappazi.app.utils.extensions.hideView
import com.example.movieappazi.app.utils.extensions.launchWhenViewStarted
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.motion.MotionListener
import com.example.movieappazi.app.utils.motion.MotionState
import com.example.movieappazi.databinding.FragmentActorsDetailsBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_actors_details.*
import kotlinx.android.synthetic.main.include_user_info_toolbar_block.*
import kotlinx.android.synthetic.main.include_user_info_toolbar_block.view.*
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ActorsDetailsFragment :
    BaseFragment<FragmentActorsDetailsBinding, ActorsDetailsFragmentViewModel>(
        FragmentActorsDetailsBinding::inflate), RvClickListener<MovieUi> {

    private val movieAdapter by lazy { MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this) }
    private val movieId: Int by lazy { ActorsDetailsFragmentArgs.fromBundle(requireArguments()).id }
    private val motionListener = MotionListener(::setToolbarState)


    @Inject
    lateinit var viewModelFactory: ActorsDetailsFragmentViewModelFactory.Factory
    override val viewModel by viewModels<ActorsDetailsFragmentViewModel> {
        viewModelFactory.create(movieId = movieId)
    }

    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observePersonDetails()
    }

    private fun setupViews() = with(requireBinding()) {
        root.addTransitionListener(motionListener)
    }


    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED -> {
                viewModel.updateMotionPosition(COLLAPSED)
                requireBinding().nestedScrollView3.smoothScrollTo(0, 0)
            }
            MotionState.EXPANDED -> viewModel.updateMotionPosition(EXPANDED)
            else -> Unit
        }
    }

    private fun observePersonDetails() = with(viewModel) {
        launchWhenViewStarted {
            personFlow.observe(::observePersonUi)

        }

        viewModel.error.onEach {
            showWarningSnackbar(it)
        }
    }

    private fun knownFor(personUi: PersonUi) = with(viewModel) {
        movieAdapter.submitList(personUi.known_for)
        requireBinding().personMoviesRv.adapter = movieAdapter
    }

    private fun observePersonUi(person: PersonDetailsUi) = with(requireBinding()) {
        backiccon.setOnDownEffectClickListener { viewModel.navigateBack() }
        includeUserInfoBlueToolbarBlock.backiccon.setOnDownEffectClickListener { viewModel.navigateBack() }
        Picasso.get().load(Utils.POSTER_PATH_URL + person.profile_path).into(userImage)
        include_user_info_toolbar_block.user_toolbar_name_text.text = person.name
        birthday.text = person.birthday
        profession.text = person.known_for_department
        biography.text = person.biography
        birthday.text = person.birthday
        if (person.gender == 1) {
            personGender.text = FEMALE
        } else personGender.text = MALE
        personName.text = person.name
        personPopularity.text = person.popularity.toFloat().toString()
        birthPlace.text = person.place_of_birth
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onItemClick(item: MovieUi) {
    }

    companion object {
        const val FEMALE = "Женский пол"
        const val MALE = "Мужской пол"
    }

    override fun onLongClick(item: MovieUi) {
        try {
            findNavController().navigate(ActorsDetailsFragmentDirections.actionActorsDetailsFragmentToMovieDetailsFragment(
                item.id!!))
        } catch (e: Exception) {
            showErrorSnackbar(e.toString())
        }
    }
}