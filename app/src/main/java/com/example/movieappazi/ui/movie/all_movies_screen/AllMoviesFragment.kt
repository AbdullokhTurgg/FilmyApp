package com.example.movieappazi.ui.movie.all_movies_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.ui.adapters.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import com.example.movieappazi.app.utils.extensions.launchWhenViewStarted
import com.example.movieappazi.app.utils.extensions.makeToast
import com.example.movieappazi.app.utils.extensions.showView
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
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

    private val popularAdapter by lazy { MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this) }

    private val upcomingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this) }

    private val publAdapter by lazy { MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this) }

    private val ratingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this) }

    private val fancyAdapter by lazy { MovieItemAdapter(MovieItemAdapter.FANCY_TYPE, this) }

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
            publishedAtMoviesFlow.observe { publAdapter.submitList(it.movies) }
            relevanceMoviesFlow.observe { upcomingAdapter.submitList(it.movies) }
            fancyMoviesFlow.observe { fancyAdapter.submitList(it.movies)
                visibilities()
            }
        }
    }

    private fun visibilities() = with(requireBinding()) {
        shimmerLayout.visibility = View.INVISIBLE
        allConst.visibility = View.VISIBLE
    }

    private fun setAdapterToRv() = with(requireBinding()) {
        popularMovieRecViewMoviesFragment.adapter = popularAdapter
        allJanrMovieRecViewMoviesFragment.adapter = upcomingAdapter
        nowPlayingMovieRecViewMoviesFragment.adapter = publAdapter
        topRatedMovieRecViewMoviesFragment.adapter = ratingAdapter
        allJanrMovieRecViewMoviesFragment2.adapter = fancyAdapter
    }

    private fun setupClickers() = with(requireBinding()) {
        see_more_popular.setOnClickListener { viewModel.goMoreMovieFragment(MovieType.POPULAR) }

        see_more_toprated.setOnClickListener { viewModel.goMoreMovieFragment(MovieType.TOP_RATED) }

        see_more_nowplaying.setOnClickListener { viewModel.goMoreMovieFragment(MovieType.NOW_PLAYING) }

        see_more_upcoming.setOnClickListener { viewModel.goMoreMovieFragment(MovieType.UPCOMING) }
    }

    override fun onLongClick(item: MovieUi) {
        viewModel.goMovieDetails(item)
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
        makeToast("${item.title} saved successfully", requireContext())
    }

    override fun onReady(savedInstanceState: Bundle?) {}
}

