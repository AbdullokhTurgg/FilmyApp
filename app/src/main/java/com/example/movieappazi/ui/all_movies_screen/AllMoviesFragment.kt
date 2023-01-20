package com.example.movieappazi.ui.all_movies_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.ui.all_nowplaying_screen.AllNowPlayingFragmentDirections
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_pubat.PublishedAtMoviesAdapter
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_toprated.TopRatedMoviesAdapter
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_upcoming.UpcomingMoviesAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AllMoviesFragment(
) : Fragment(), RvClickListener<MovieUi> {

    private val binding by lazy {
        FragmentAllMoviesBinding.inflate(layoutInflater)
    }
    private val viewModel: AllMoviesFragmentViewModel by viewModels()

    private val popularAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val upcomingAdapter: UpcomingMoviesAdapter by lazy {
        UpcomingMoviesAdapter(objectViewType = UpcomingMoviesAdapter.PORTRAIT_TYPE, this)
    }
    private val publishedAtAdapter: PublishedAtMoviesAdapter by lazy {
        PublishedAtMoviesAdapter(objectViewType = UpcomingMoviesAdapter.PORTRAIT_TYPE, this)
    }
    private val ratingAdapter: TopRatedMoviesAdapter by lazy {
        TopRatedMoviesAdapter(objectViewType = TopRatedMoviesAdapter.PORTRAIT_TYPE, this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickers()
        setAdapterToRv()
        observePopularMovies()
        observeRelevancyMovies()
        observePublishedAtMovies()
        observeRatingMovies()
    }

    private fun setAdapterToRv() {
        binding.moviesRv.adapter = popularAdapter
        binding.relevancyMoviesRv.adapter = upcomingAdapter
        binding.pubatMoviesRv.adapter = publishedAtAdapter
        binding.ratingMoviesRv.adapter = ratingAdapter
    }

    private fun observeRatingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.ratingMovies.collectLatest {
                ratingAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePublishedAtMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.publishedAtMovies.collectLatest {
                publishedAtAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePopularMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.popularMovies.collectLatest {
                popularAdapter.submitList(it.movies)
            }
        }
    }

    private fun observeRelevancyMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.relevanceMovies.collectLatest {
                upcomingAdapter.submitList(it.movies)
            }
        }
    }

    private fun setupClickers() {
        binding.seeAllTvForPopular.setOnClickListener {
            findNavController().navigate(AllMoviesFragmentDirections.actionNavMoviesToAllPopularMoviesFragment())
        }
        binding.seeAllTvForRelevancy.setOnClickListener {
            findNavController().navigate(AllMoviesFragmentDirections.actionNavMoviesToAllNowPlayingFragment())
        }
        binding.seeAllTvForPubat.setOnClickListener {
            findNavController().navigate(AllMoviesFragmentDirections.actionNavMoviesToAllTopRatedMoviesFragment())
        }
        binding.seeAllTvForRating.setOnClickListener {
            findNavController().navigate(AllMoviesFragmentDirections.actionNavMoviesToAllUpcomingFragment())
        }
    }

    override fun onItemClick(item: MovieUi) {
    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(AllMoviesFragmentDirections.actionAllMoviesFragmentToMovieDetailsFragment(
            item))
    }
}

