package com.example.movieappazi.ui.see_all_movies_screen

import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentSeeMoreBinding
import com.example.movieappazi.extensions.hideView
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.flow.collectLatest

@Parcelize
enum class MovieType : Parcelable {
    POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING,
}

@AndroidEntryPoint
class SeeAllMoviesFragment :
    BaseFragment<FragmentSeeMoreBinding, SeeAllMoviesFragmentViewModel>(FragmentSeeMoreBinding::inflate),
    RvClickListener<MovieUi> {
    override fun onReady(savedInstanceState: Bundle?) {}

    private val args by navArgs<SeeAllMoviesFragmentArgs>()
    private val ratingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this)
    }
    private val popularAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this)
    }
    private val upcomingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this)
    }
    private val nowPlayingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.SEEMORETYPE, this)
    }
    override val viewModel: SeeAllMoviesFragmentViewModel by viewModels()

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickers()
        setupTypes()
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
                allTopratedRv.adapter = ratingAdapter
                observeRatingsMovie()
            }
            MovieType.UPCOMING -> {
                allTopratedRv.adapter = upcomingAdapter
                observeUpcomingMovies()
            }
            MovieType.POPULAR -> {
                allTopratedRv.adapter = popularAdapter
                observePopularMovies()
            }
            MovieType.NOW_PLAYING -> {
                allTopratedRv.adapter = nowPlayingAdapter
                observeNowPlayingMovies()
            }
        }
    }

    private fun observeRatingsMovie() {
        lifecycleScope.launchWhenResumed {
            viewModel.allTopRatedMovies.collectLatest {
                ratingAdapter.submitList(it.movies)
                requireBinding().pageConstraint.visibility = View.VISIBLE
            }
        }
    }

    private fun observePopularMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allPopularMovies.collectLatest {
                popularAdapter.submitList(it.movies)
                requireBinding().pageConstraint.visibility = View.VISIBLE
            }
        }
        lifecycleScope.launchWhenResumed {
            viewModel.movieResponseState.collectLatest { state ->
                requireBinding().apply {
                    prevPageText.text = state.previousPage.toString()
                    currentPageText.text = state.page.toString()
                    nextPageText.text = state.nextPage.toString()
                    prevBtn.apply {
                        isClickable = state.isHasPreviousPage
                        isFocusable = state.isHasPreviousPage
                    }
                    nextBtn.apply {
                        isClickable = state.isHasNextPage
                        isFocusable = state.isHasNextPage
                    }
                }
            }
        }
    }

    private fun observeUpcomingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allUpcomingMovies.collectLatest {
                upcomingAdapter.submitList(it.movies)
                requireBinding().pageConstraint.visibility = View.VISIBLE
            }
        }
    }

    private fun observeNowPlayingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allNowPlayingMovies.collectLatest {
                nowPlayingAdapter.submitList(it.movies)
                requireBinding().pageConstraint.visibility = View.VISIBLE
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