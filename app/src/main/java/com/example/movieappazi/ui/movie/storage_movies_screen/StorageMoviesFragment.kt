package com.example.movieappazi.ui.movie.storage_movies_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.recyclerview.animations.AddableItemAnimator
import com.example.movieappazi.app.recyclerview.animations.custom.SlideInLeftCommonAnimator
import com.example.movieappazi.app.utils.extensions.hideView
import com.example.movieappazi.app.utils.extensions.launchWhenViewStarted
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.showView
import com.example.movieappazi.databinding.FragmentStorageMoviesBinding
import com.example.movieappazi.ui.adapters.movie.SavedMoviesAdapter
import com.example.movieappazi.ui.adapters.movie.TvStorageAdapter
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllSeriesFragment.Companion.DEFAULT_ITEMS_ANIMATOR_DURATION
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

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

        initObserverMovie()
        adapterAnimation()
        setupClicks()
    }

    private fun initObserverMovie() = with(viewModel) {
        launchWhenViewStarted {
            storageMoviesFlow.observe {
                movieAdapter.submitList(it)
                visibilities()
            }
        }
        requireBinding().rvFavMovies.adapter = movieAdapter
    }

    private fun initOberverSeries() = with(viewModel) {
        launchWhenViewStarted { tvStorageFlow.observe { tvAdapter.submitList(it)
                visibilities() } }
        requireBinding().rvFavMovies.adapter = tvAdapter
    }

    private fun setupClicks() = with(requireBinding()) {
        moviesBtn.setOnDownEffectClickListener { initObserverMovie() }
        seriesBtn.setOnDownEffectClickListener { initOberverSeries() }
    }

    private fun visibilities() {
        requireBinding().constSaved.showView()
        requireBinding().emptyImg.hideView()
        requireBinding().savedProgresss.hideView()
    }

    private fun adapterAnimation() = with(requireBinding()) {
        rvFavMovies.itemAnimator =
            AddableItemAnimator(SlideInLeftCommonAnimator()).also { animator ->
                animator.addViewTypeAnimation(R.layout.item_fav_movies, SlideInLeftCommonAnimator())
                animator.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
                animator.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            }
    }


    override fun onItemClick(movie: MovieUi) {
        viewModel.launchMovieDetails(movie)
    }

    override fun onClearItemClick(movie: MovieUi) {
        viewModel.deleteMovies(movies = movie.id)
    }

    override fun onItemClick(seriesUi: SeriesUi) {
        viewModel.launchTvDetails(seriesUi)
    }

    override fun onTvClearItemClick(seriesUi: SeriesUi) {
        viewModel.deleteTV(seriesUi.id)
    }

}