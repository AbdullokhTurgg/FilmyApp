package com.example.movieappazi.ui.search_movies_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentSearchMoviesBinding
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.extensions.showView
import com.example.movieappazi.ui.movie_details_screen.MovieDetailsFragmentArgs
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_movie_genres.MovieCategoriesAdapter
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_search_movies.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class SearchMoviesFragment :
    BaseFragment<FragmentSearchMoviesBinding, SearchMoviesFragmentViewModel>(
        FragmentSearchMoviesBinding::inflate,
    ), RvClickListener<MovieUi> {

    override fun onReady(savedInstanceState: Bundle?) {}

    override val viewModel: SearchMoviesFragmentViewModel by viewModels()

    private val moviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()

        search()
        setUi()
        observe()
    }

    private fun setUi() = with(requireBinding()) {
        moviesRv.adapter = moviesAdapter
    }

    private fun observe() = with(requireBinding()) {
        viewModel.error.onEach {
            makeToast(it, requireContext())
        }

        lifecycleScope.launchWhenResumed {
            viewModel.movies.collectLatest {
                moviesAdapter.submitList(it.movies)
                searchViewLottie.visibility = View.INVISIBLE
                requireBinding().shimmerLayout.visibility = View.INVISIBLE
            }
        }
    }

    private fun search() = with(requireBinding()) {
        requireBinding().shimmerLayout.visibility = View.VISIBLE

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                requireBinding().shimmerLayout.visibility = View.VISIBLE
                if (query != null) {
                    viewModel.searchMovie(query)
                }
                return false
            }

            override fun onQueryTextChange(query: String?): Boolean {
                requireBinding().shimmerLayout.visibility = View.VISIBLE

                if (query != null) {
                    viewModel.searchMovie(query)
                }
                return false
            }
        })
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(movie = item)
        makeToast("Movie ${item.title} saved", requireContext())
    }

    override fun onLongClick(item: MovieUi) {
        viewModel.launchMovieDetails(item)
    }
}