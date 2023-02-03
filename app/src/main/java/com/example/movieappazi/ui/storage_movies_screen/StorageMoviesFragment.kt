package com.example.movieappazi.ui.storage_movies_screen

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieappazi.R
import com.example.movieappazi.adapter.animations.AddableItemAnimator
import com.example.movieappazi.adapter.animations.SlideInLeftAnimator
import com.example.movieappazi.adapter.animations.custom.SlideInLeftCommonAnimator
import com.example.movieappazi.adapter.animations.custom.SlideInTopCommonAnimator
import com.example.movieappazi.databinding.FragmentStorageMoviesBinding
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.SavedMoviesAdapter
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.newsappazi.adapter.decorations.FeedHorizontalDividerItemDecoration
import com.example.newsappazi.adapter.decorations.GroupVerticalItemDecoration
import com.example.movieappazi.adapter.fingerprints.FingerPrintAdapter
import com.example.movieappazi.adapter.fingerprints.PostFingerPrint
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_storage_movies.*
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart

@AndroidEntryPoint
class StorageMoviesFragment : Fragment() {
    private val binding by lazy {
        FragmentStorageMoviesBinding.inflate(layoutInflater)
    }
    private val viewModel: StorageMoviesFragmentViewModel by viewModels()

    private lateinit var adapter: FingerPrintAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    private lateinit var builder: AlertDialog.Builder
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupAdapter()
        animator()
        submitInitialListWithDelayForAnimation()
//        initObserver()

        builder = AlertDialog.Builder(requireContext())
    }

    private fun setupAdapter() {
        adapter = FingerPrintAdapter(getFingerprints())
    }

    private fun animator() = with(binding.rvFavMovies) {
        layoutManager = LinearLayoutManager(requireContext())
        adapter = this.adapter

        addItemDecoration(FeedHorizontalDividerItemDecoration(70)) // addable
        addItemDecoration(GroupVerticalItemDecoration(R.layout.item_fav_movies, 100, 0))

        itemAnimator = AddableItemAnimator(SlideInLeftCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(R.layout.item_fav_movies, SlideInLeftCommonAnimator())
        }
        itemAnimator = AddableItemAnimator(SlideInTopCommonAnimator()).also { animator ->
            animator.addViewTypeAnimation(R.layout.item_fav_movies, SlideInTopCommonAnimator())
            animator.addDuration = 500L
            animator.removeDuration = 500L
        }
    }

    private fun getFingerprints() = listOf(PostFingerPrint())

    private fun submitInitialListWithDelayForAnimation() {
        binding.rvFavMovies.postDelayed({
            lifecycleScope.launchWhenStarted {
                viewModel.allMoviesFlow.onEach { movies ->
                    savedProgresss.isVisible = false
                    listOfFavouritesObserving(movies)
                }.onStart {
                    savedProgresss.isVisible = true
                }.catch {
                    savedProgresss.isVisible = false
                }.collect()
            }
        }, 400L)
    }

//    private fun initObserver() = with(viewModel) {
//        binding.apply {
//            lifecycleScope.launchWhenStarted {
//                allMoviesFlow.onEach { articles ->
//                    savedProgresss.isVisible = false
//                    listOfFavouritesObserving(articles)
//                }.onStart {
//                    savedProgresss.isVisible = true
//                }.catch {
//                    savedProgresss.isVisible = false
//                }.collect()
//            }
//
//        }
//    }

    private fun listOfFavouritesObserving(list: List<MovieUi>) {
        lifecycleScope.launchWhenResumed {
            viewModel.allMoviesFlow.collect {
                adapter.submitList(list)

                binding.rvFavMovies.adapter = SavedMoviesAdapter(list,
                    object : SavedMoviesAdapter.RecyclerFavOnClickListener {
                        override fun onLongClick(position: Int) {
                            builder.setTitle("News App").setMessage("Do you want to delete?")
                                .setCancelable(true)
                                .setPositiveButton("Yes") { dialogInterface, it ->
                                    list[position].id.let { viewModel.deleteMovies(it!!) }
                                    makeToast("News (${list[position].title}) Удалён",
                                        requireContext())
                                    submitInitialListWithDelayForAnimation()
                                }.setNegativeButton("No") { dialogInterface, it ->
                                    dialogInterface.cancel()

                                }.show()
                        }

                        override fun onItemClick(position: Int) {
                          
                        }
                    })
            }
        }
    }
}
