package com.example.movieappazi.ui.search_movies_screen

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentSearchMoviesBinding
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_movie_genres.MovieCategoriesAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMoviesFragment : Fragment() {

    private val viewModel: SearchMoviesFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {

        val navBar: BottomNavigationView = requireActivity().findViewById(R.id.bottomNavMenu)
        navBar.visibility = View.VISIBLE

        val binding = FragmentSearchMoviesBinding.inflate(inflater)


        val adapter = MovieCategoriesAdapter()
        val recyclerView = binding.genres
        recyclerView.layoutManager = GridLayoutManager(context, 2)
        recyclerView.adapter = adapter

//        lifecycleScope.launchWhenStarted {
//            viewModel.uiState.collect { uiState ->
//                when {
//                    uiState.categories.isNotEmpty() -> {
//                        adapter.submitList(uiState.categories)
//                        binding.categoriesProgressBar.visibility = View.GONE
//
//                    }
//                    uiState.isLoading -> {
//                        binding.categoriesProgressBar.visibility = View.VISIBLE
//
//                    }
//                    uiState.error.isNotEmpty() -> {}
//                }
//            }
//        }

        return binding.root
    }


}