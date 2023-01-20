package com.example.movieappazi.ui.all_toprated_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentAllTopRatedMoviesBinding
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_toprated.TopRatedMoviesAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AllTopRatedMoviesFragment : Fragment(), RvClickListener<MovieUi> {
    private val binding by lazy {
        FragmentAllTopRatedMoviesBinding.inflate(layoutInflater)
    }
    private val ratingAdapter: TopRatedMoviesAdapter by lazy {
        TopRatedMoviesAdapter(TopRatedMoviesAdapter.PORTRAIT_TYPE, this)
    }
    private val viewModel: AllTopRatedMoviesFragmentViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeRatingsMovie()
    }

    private fun setupRecyclerView() {
        binding.allTopratedRv.adapter = ratingAdapter
    }

    private fun observeRatingsMovie() {
        lifecycleScope.launchWhenResumed {
            viewModel.allTopRatedMovies.collectLatest {
                ratingAdapter.submitList(it.movies)
            }
        }
    }

    override fun onItemClick(item: MovieUi) {

    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(AllTopRatedMoviesFragmentDirections.actionAllTopRatedMoviesFragmentToMovieDetailsFragment(
            item))
    }
}