package com.example.movieappazi.ui.all_movies_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.extensions.launchWhenViewStarted
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.extensions.showView
import com.example.movieappazi.ui.adapters.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.see_all_movies_screen.MovieType
import com.example.movieappazi.uiModels.movie.MovieUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_item_for_movie_det.*
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.android.synthetic.main.fragment_root.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi

@OptIn(ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
@AndroidEntryPoint
class AllMoviesFragment :
    BaseFragment<FragmentAllMoviesBinding, AllMoviesFragmentViewModel>(FragmentAllMoviesBinding::inflate),
    RvClickListener<MovieUi> {

    override val viewModel: AllMoviesFragmentViewModel by viewModels()

    private val popularAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this)
    }
    private val upcomingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(objectViewType = MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val publishedAtAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(objectViewType = MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val ratingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(objectViewType = MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val fancyAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(objectViewType = MovieItemAdapter.FANCY_TYPE, this)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterToRv()
        setupClickers()
        observeData()
    }

    private fun observeData() = with(viewModel) {
        launchWhenViewStarted {
            popularMoviesFlow.observe { popularAdapter.submitList(it.movies) }
            ratingMoviesFlow.observe { ratingAdapter.submitList(it.movies) }
            publishedAtMoviesFlow.observe { publishedAtAdapter.submitList(it.movies) }
            relevanceMoviesFlow.observe { upcomingAdapter.submitList(it.movies) }
            fancyMoviesFlow.observe { fancyAdapter.submitList(it.movies) }
            requireBinding().shimmerLayout.visibility = View.INVISIBLE
            requireBinding().allConst.visibility = View.VISIBLE
        }
    }

    private fun setAdapterToRv() = with(requireBinding()) {
        popularMovieRecViewMoviesFragment.adapter = popularAdapter
        allJanrMovieRecViewMoviesFragment.adapter = upcomingAdapter
        nowPlayingMovieRecViewMoviesFragment.adapter = publishedAtAdapter
        topRatedMovieRecViewMoviesFragment.adapter = ratingAdapter
        allJanrMovieRecViewMoviesFragment2.adapter = fancyAdapter
    }

    private fun setupClickers() = with(requireBinding()) {
        see_more_popular.setOnClickListener { launchToSeeAllFragment(MovieType.POPULAR) }

        see_more_toprated.setOnClickListener { launchToSeeAllFragment(MovieType.TOP_RATED) }

        see_more_nowplaying.setOnClickListener { launchToSeeAllFragment(MovieType.NOW_PLAYING) }

        see_more_upcoming.setOnClickListener { launchToSeeAllFragment(MovieType.UPCOMING) }
    }

    private fun launchToSeeAllFragment(type: MovieType) = viewModel.goMoreMovieFragment(type)

    override fun onLongClick(item: MovieUi) {
        viewModel.goMovieDetails(item = item)
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
        makeToast("${item.title} saved successfully", requireContext())
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}

