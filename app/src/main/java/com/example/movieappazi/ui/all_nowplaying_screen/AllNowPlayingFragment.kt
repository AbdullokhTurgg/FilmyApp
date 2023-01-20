package com.example.movieappazi.ui.all_nowplaying_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.databinding.FragmentAllNowPlayingBinding
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_pubat.PublishedAtMoviesAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AllNowPlayingFragment : Fragment(), RvClickListener<MovieUi> {
    private val binding by lazy {
        FragmentAllNowPlayingBinding.inflate(layoutInflater)
    }
    private val onScreenAdapter: PublishedAtMoviesAdapter by lazy {
        PublishedAtMoviesAdapter(PublishedAtMoviesAdapter.PORTRAIT_TYPE, this)
    }
    private val viewModel: AllNowPlayingFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        observeOnScreenMovies()
    }

    private fun setupRecyclerView() {
        binding.allOnscreenRv.adapter = onScreenAdapter
    }

    private fun observeOnScreenMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allNowPlayingMovies.collectLatest {
                onScreenAdapter.submitList(it.movies)
            }
        }
    }

    override fun onItemClick(item: MovieUi) {

    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(AllNowPlayingFragmentDirections.actionAllNowPlayingFragmentToMovieDetailsFragment(
            item))
    }
}