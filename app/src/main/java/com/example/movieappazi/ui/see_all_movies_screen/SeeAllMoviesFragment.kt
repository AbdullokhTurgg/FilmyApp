package com.example.movieappazi.ui.see_all_movies_screen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentSeeMoreBinding
import com.example.movieappazi.extensions.hideView
import com.example.movieappazi.extensions.launchWhenViewStarted
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.adapters.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize

@Parcelize
enum class MovieType : Parcelable {
    POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING,
}

@AndroidEntryPoint
class SeeAllMoviesFragment :
    BaseFragment<FragmentSeeMoreBinding, SeeAllMoviesFragmentViewModel>(FragmentSeeMoreBinding::inflate),
    RvClickListener<MovieUi> {

    override val viewModel: SeeAllMoviesFragmentViewModel by viewModels()

    override fun onReady(savedInstanceState: Bundle?) {}

    private val args by navArgs<SeeAllMoviesFragmentArgs>()

    private val ratingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }

    private val popularAdapter by lazy { MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this) }

    private val upcomingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }

    private val nowPlayingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this) }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickers()
        setupTypes()
    }

    private fun observeAllNowPlaying() = with(viewModel) {
        launchWhenViewStarted { allNowPlayingMovies.observe { nowPlayingAdapter.submitList(it.movies) } }
    }

    private fun observeAllUpcoming() = with(viewModel) {
        launchWhenViewStarted { allUpcomingMovies.observe { upcomingAdapter.submitList(it.movies) } }
    }

    private fun observeAllPopular() = with(viewModel) {
        launchWhenViewStarted { allPopularMovies.observe { popularAdapter.submitList(it.movies) } }
    }

    private fun observeAllTopRated() = with(viewModel) {
        launchWhenViewStarted { allTopRatedMovies.observe { ratingAdapter.submitList(it.movies) } }
    }


    private fun setupClickers() = with(requireBinding()) {
        nextBtn.setOnClickListener {
            viewModel.nextPage()
            scrollView.fullScroll(ScrollView.FOCUS_UP)
        }
        prevBtn.setOnClickListener {
            viewModel.previousPage()
            scrollView.fullScroll(ScrollView.FOCUS_UP)
        }
    }

    private fun setupTypes() = with(requireBinding()) {
        when (args.type) {
            MovieType.TOP_RATED -> {
                moviesRv.adapter = ratingAdapter
                observeAllTopRated()
            }
            MovieType.UPCOMING -> {
                moviesRv.adapter = upcomingAdapter
                observeAllUpcoming()
            }
            MovieType.POPULAR -> {
                moviesRv.adapter = popularAdapter
                observeAllPopular()
            }
            MovieType.NOW_PLAYING -> {
                moviesRv.adapter = nowPlayingAdapter
                observeAllNowPlaying()
            }
        }
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
        makeToast("${item.title} saved successfully", requireContext())
    }

    override fun onLongClick(item: MovieUi) {
        viewModel.goMovieDetailsFragment(item)
    }
}