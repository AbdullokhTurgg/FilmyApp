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
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentStorageMoviesBinding
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.SavedMoviesAdapter
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.AndroidEntryPoint
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

        initObserver()

        builder = AlertDialog.Builder(requireContext())
    }

    private fun initObserver() = with(viewModel) {
        binding.apply {
            lifecycleScope.launchWhenStarted {
                allMoviesFlow.onEach { articles ->
                    savedProgresss.isVisible = false
                    listOfFavouritesObserving(articles)
                }.onStart {
                    savedProgresss.isVisible = true
                }.catch {
                    savedProgresss.isVisible = false
                }.collect()
            }

        }
    }

    private fun listOfFavouritesObserving(list: List<MovieUi>) {

        binding.rvFavMovies.adapter =
            SavedMoviesAdapter(list, object : SavedMoviesAdapter.RecyclerFavOnClickListener {

                override fun onItemClick(position: Int) {
                    builder.setTitle("News App").setMessage("Do you want to delete?")
                        .setCancelable(true).setPositiveButton("Yes") { dialogInterface, it ->
                            list[position].id.let { viewModel.deleteMovies(it) }
                            makeToast("News (${list[position].title}) Удалён", requireContext())
                            initObserver()
                        }.setNegativeButton("No") { dialogInterface, it ->
                            dialogInterface.cancel()

                        }.show()


                }

                override fun onClearItemClick(position: Int) {
//                    findNavController().navigate(SavedNewsFragmentDirections.actionNavigationFavouriteToDetailsFragment())
                }
            })
    }

}