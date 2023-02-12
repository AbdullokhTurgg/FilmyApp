package com.example.movieappazi.ui.storage_movies_screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappazi.R
import com.example.movieappazi.adapter.animations.AddableItemAnimator
import com.example.movieappazi.adapter.animations.custom.SlideInLeftCommonAnimator
import com.example.movieappazi.adapter.fingerprints.FingerprintAdapter
import com.example.movieappazi.adapter.fingerprints.PostFingerPrint
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentStorageMoviesBinding
import com.example.movieappazi.extensions.showView
import com.example.movieappazi.ui.adapters.movie.adapter_for_popular.SavedMoviesAdapter
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.newsappazi.adapter.decorations.FeedHorizontalDividerItemDecoration
import com.example.newsappazi.adapter.decorations.GroupVerticalItemDecoration
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class StorageMoviesFragment :
    BaseFragment<FragmentStorageMoviesBinding, StorageMoviesFragmentViewModel>(
        FragmentStorageMoviesBinding::inflate), SavedMoviesAdapter.RecyclerFavOnClickListener {

    override fun onReady(savedInstanceState: Bundle?) {}

    override val viewModel: StorageMoviesFragmentViewModel by viewModels()

    private lateinit var adapter: FingerprintAdapter

    private val movieAdapter by lazy { SavedMoviesAdapter(this) }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    private lateinit var builder: AlertDialog.Builder

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        animator()
        requireBinding().rvFavMovies.adapter = movieAdapter
        initObserver()

        builder = AlertDialog.Builder(requireContext())
    }

    private fun setupAdapter() {
        adapter = FingerprintAdapter(getFingerprints())
    }

    private fun animator() = with(requireBinding().rvFavMovies) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this.adapter

        addItemDecoration(FeedHorizontalDividerItemDecoration(70)) // addable
        addItemDecoration(GroupVerticalItemDecoration(R.layout.item_fav_movies, 100, 0))

        itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(R.layout.item_fav_movies, SlideInLeftCommonAnimator())
        }

        itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { animator ->
            animator.addViewTypeAnimation(R.layout.item_fav_movies, SlideInLeftCommonAnimator())
            animator.addDuration = 500L
            animator.removeDuration = 500L
        }
    }

    private fun getFingerprints() = listOf(PostFingerPrint())

    private fun initObserver() = with(viewModel) {
        requireBinding().apply {
            rvFavMovies.postDelayed({
                lifecycleScope.launchWhenStarted {
                    storageMovies.collectLatest {
                        adapter.submitList(it)
                        requireBinding().savedProgresss.isVisible = it.isEmpty()
                    }
                }
            }, 400L)
        }
    }

    override fun onLongClick(movieUi: MovieUi) {
        viewModel.deleteMovies(movieUi.id!!)
    }

    override fun onItemClick(movieUi: MovieUi) {
        viewModel.launchMovieDetails(movieUi)
    }
}
