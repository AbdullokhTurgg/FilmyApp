package com.example.movieappazi.ui.movie.storage_movies_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.utils.recyclerview.animations.AddableItemAnimator
import com.example.movieappazi.app.utils.recyclerview.animations.custom.SlideInLeftCommonAnimator
import com.example.movieappazi.app.utils.extensions.hideView
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.showView
import com.example.movieappazi.databinding.FragmentStorageMoviesBinding
import com.example.movieappazi.ui.adapters.movie.SavedMoviesAdapter
import com.example.movieappazi.ui.adapters.movie.TvStorageAdapter
import com.example.ui_core.extensions.extensions.launchWhenViewStarted
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class StorageMoviesFragment :
    BaseFragment<FragmentStorageMoviesBinding, StorageMoviesFragmentViewModel>(
        FragmentStorageMoviesBinding::inflate), SavedMoviesAdapter.RecyclerFavOnClickListener,
    TvStorageAdapter.RecyclerFavOnClickListener {

    override fun onReady(savedInstanceState: Bundle?) {}
    override val viewModel: StorageMoviesFragmentViewModel by viewModels()

    private val movieAdapter by lazy { SavedMoviesAdapter(this) }
    private val tvAdapter by lazy { TvStorageAdapter(this) }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRv()
        initObserverMovie()
        recyclerViewAnim()
        setupClicks()
    }

    private fun setupRv() {
        requireBinding().rvFavMovies.adapter = movieAdapter
        requireBinding().rvFavMovies.adapter = tvAdapter

    }

    private fun initObserverMovie() = with(viewModel) {
        requireBinding().rvFavMovies.adapter = movieAdapter
        launchWhenViewStarted { storageMoviesFlow.observe { movieAdapter.moviesList = it
                visibilities() } }
            error.onEach { showErrorSnackbar(it) }
    }

    private fun initObserverSeries() = with(viewModel) {
        requireBinding().rvFavMovies.adapter = tvAdapter
        launchWhenViewStarted { tvStorageFlow.observe { tvAdapter.submitList(it)
                visibilities() } }
        error.onEach { showErrorSnackbar(it) }

    }

    private fun setupClicks() = with(requireBinding()) {
        moviesBtn.setOnDownEffectClickListener { initObserverMovie() }
        seriesBtn.setOnDownEffectClickListener { initObserverSeries() }
    }

    private fun visibilities() {
        requireBinding().constSaved.showView()
        requireBinding().emptyImg.hideView()
        requireBinding().savedProgresss.hideView()
    }

    private fun recyclerViewAnim() = with(requireBinding()) {
        rvFavMovies.itemAnimator =
            AddableItemAnimator(SlideInLeftCommonAnimator()).also { animator ->
                animator.addViewTypeAnimation(R.layout.item_fav_movies, SlideInLeftCommonAnimator())
                animator.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
                animator.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            }
    }


    override fun onItemClick(movie: MovieUi) {
        try {viewModel.launchMovieDetails(movie) }
        catch (e:Exception){showErrorSnackbar(e.toString())}
    }

    override fun onClearItemClick(movie: MovieUi) {
        try {viewModel.deleteMovies(movies = movie.id!!)}
        catch (e:Exception){showErrorSnackbar(e.toString())}
    }

    override fun onItemClick(seriesUi: SeriesUi) {
        try { viewModel.launchTvDetails(seriesUi) }
        catch (e:Exception){showErrorSnackbar(e.toString())}
    }

    override fun onTvClearItemClick(seriesUi: SeriesUi) {
        try { viewModel.deleteTV(seriesUi.id) }
        catch (e:Exception){showErrorSnackbar(e.toString())}
    }
}