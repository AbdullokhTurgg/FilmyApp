package com.example.movieappazi.ui.movie.all_movies_screen

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.extensions.*
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.movie.all_movies_screen.adapter.fingerprints.*
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllSeriesFragment.Companion.PRESSONCE
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.android.synthetic.main.fragment_root.*
import kotlinx.android.synthetic.main.item_popular_movies.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.filterNotNull

@OptIn(ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
@AndroidEntryPoint
class AllMoviesFragment :
    BaseFragment<FragmentAllMoviesBinding, AllMoviesFragmentViewModel>(FragmentAllMoviesBinding::inflate),
    RvClickListener<MovieUi> {

    override fun onReady(savedInstanceState: Bundle?) {}
    override val viewModel: AllMoviesFragmentViewModel by viewModels()

    private val popularAdapter by lazy { MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this) }
    private val upcomingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val publAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }
    private val ratingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }


    private fun setupClickers() = with(requireBinding()) {
        seeMoreToprated.setOnDownEffectClickListener { viewModel.goMoreMovieFragment(MovieType.TOP_RATED) }
        seeMoreNowplaying.setOnDownEffectClickListener { viewModel.goMoreMovieFragment(MovieType.NOW_PLAYING) }
        seeMoreUpcoming.setOnDownEffectClickListener { viewModel.goMoreMovieFragment(MovieType.UPCOMING) }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterToRv()
        observeMovie()
        observeData()
        setupClickers()
    }

    private fun observeMovie() = with(viewModel) {
        launchWhenViewStarted {
            allFilteredMovieItems.filterNotNull().observe {
                visibilities()
                observeData()
            }
        }
    }

    private fun observeData() = with(viewModel) {
        launchWhenViewStarted {
            popularMoviesFlow.observe { popularAdapter.submitList(it.movies) }
            ratingMoviesFlow.observe { upcomingAdapter.submitList(it.movies) }
            publishedAtMoviesFlow.observe { publAdapter.submitList(it.movies) }
            relevanceMoviesFlow.observe {
                ratingAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun setAdapterToRv() = with(requireBinding()) {
        popularMovieRecViewMoviesFragment.adapter = popularAdapter
        allJanrMovieRecViewMoviesFragment.adapter = upcomingAdapter
        nowPlayingMovieRecViewMoviesFragment.adapter = publAdapter
        topRatedMovieRecViewMoviesFragment.adapter = ratingAdapter
    }

    private fun visibilities() {
        requireBinding().shimmerLayout.visibility = View.INVISIBLE
        requireBinding().allConst.showView()
    }

    override fun onLongClick(item: MovieUi) {
        viewModel.goMovieDetails(item)
    }

    override fun onItemClick(item: MovieUi) {
        showErrorSnackbar(PRESSONCE)
    }
}

