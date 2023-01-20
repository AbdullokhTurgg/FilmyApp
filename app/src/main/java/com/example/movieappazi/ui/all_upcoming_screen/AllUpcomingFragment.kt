package com.example.movieappazi.ui.all_upcoming_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentAllUpcomingBinding
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_upcoming.UpcomingMoviesAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AllUpcomingFragment : Fragment(), RvClickListener<MovieUi> {
    private val binding by lazy {
        FragmentAllUpcomingBinding.inflate(layoutInflater)
    }
    private val upcomingAdapter: UpcomingMoviesAdapter by lazy {
        UpcomingMoviesAdapter(UpcomingMoviesAdapter.PORTRAIT_TYPE, this)
    }
    private val viewModel: AllUpcomingFragmentViewModel by viewModels()

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
        observeUpcomingMovies()
    }

    private fun setupRecyclerView() {
        binding.allUpcomingRv.adapter = upcomingAdapter
    }

    private fun observeUpcomingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.allTopRatedMovies.collectLatest {
                upcomingAdapter.submitList(it.movies)
            }
        }
    }

    override fun onItemClick(item: MovieUi) {

    }

    override fun onLongClick(item: MovieUi) {
    }
}