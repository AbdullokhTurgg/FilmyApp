package com.example.movieappazi.ui.movie.search_movies_screen

import android.os.Bundle
import android.view.View
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.extensions.hideView
import com.example.movieappazi.app.utils.extensions.launchWhenViewStarted
import com.example.movieappazi.app.utils.extensions.makeToast
import com.example.movieappazi.app.utils.extensions.showView
import com.example.movieappazi.app.utils.motion.MotionListener
import com.example.movieappazi.app.utils.motion.MotionState
import com.example.movieappazi.databinding.FragmentSearchMoviesBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchMoviesFragment :
    BaseFragment<FragmentSearchMoviesBinding, SearchMoviesFragmentViewModel>(
        FragmentSearchMoviesBinding::inflate,
    ), RvClickListener<MovieUi> {

    override fun onReady(savedInstanceState: Bundle?) {}
    override val viewModel: SearchMoviesFragmentViewModel by viewModels()

    private val moviesSearchAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val motionListener = MotionListener(::setToolbarState)

    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        search()
        setUi()
    }

    private fun setUi() = with(requireBinding()) {
        moviesRv.adapter = moviesSearchAdapter
    }

    private fun observeData(key: String) = with(viewModel) {
        requireBinding().searchPb.visibility = View.INVISIBLE
        launchWhenViewStarted {
            searchMovie(key).observe {
                moviesSearchAdapter.submitList(it.movies)
                error.onEach {
                    makeToast(it, requireContext())
                }
            }
        }
    }

    private fun setupViews() = with(requireBinding()) {
        root.addTransitionListener(motionListener)
    }


    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED -> {
                viewModel.updateMotionPosition(COLLAPSED)
            }

            MotionState.EXPANDED -> viewModel.updateMotionPosition(EXPANDED)
            else -> Unit
        }
    }

    private fun search() = with(requireBinding()) {
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    observeData(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null) {
                    observeData(query)
                }
                return false
            }
        })
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(movie = item)
        showSuccessSnackBar("Movie ${item.title} saved")
    }

    override fun onLongClick(item: MovieUi) {
        viewModel.launchMovieDetails(item)
    }

}