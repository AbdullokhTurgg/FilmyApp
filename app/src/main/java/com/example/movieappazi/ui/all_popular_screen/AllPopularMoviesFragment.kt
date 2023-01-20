package com.example.movieappazi.ui.all_popular_screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentAllPopularMoviesBinding
import com.example.movieappazi.ui.all_movies_screen.AllMoviesFragmentViewModel
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class AllPopularMoviesFragment : Fragment(), RvClickListener<MovieUi> {

    private val binding by lazy {
        FragmentAllPopularMoviesBinding.inflate(layoutInflater)
    }

    private val popularAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
    }
    private val viewModel: AllPopularMoviesFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecyclerViews()
        observePopularMovies()
    }

    private fun setUpRecyclerViews() {
        binding.allPopularRv.adapter = popularAdapter
    }

    private fun observePopularMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.Popularmovies.collectLatest {
                popularAdapter.submitList(it.movies)

            }
        }
    }

    override fun onItemClick(item: MovieUi) {
    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(AllPopularMoviesFragmentDirections.actionAllPopularMoviesFragmentToMovieDetailsFragment(
            item))
    }
}