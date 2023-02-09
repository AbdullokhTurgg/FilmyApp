package com.example.movieappazi.ui.all_movies_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.data.network.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.extensions.showView
import com.example.movieappazi.ui.see_all_movies_screen.MovieType
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_item_for_movie_det.*
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.android.synthetic.main.fragment_root.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
@AndroidEntryPoint
class AllMoviesFragment :
    BaseFragment<FragmentAllMoviesBinding, AllMoviesFragmentViewModel>(FragmentAllMoviesBinding::inflate),
    RvClickListener<MovieUi> {

    override val viewModel: AllMoviesFragmentViewModel by viewModels()

    private val popularAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this)
    }

    private val upcomingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(objectViewType = MovieItemAdapter.PORTRAIT_TYPE, this)
    }

    private val publishedAtAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(objectViewType = MovieItemAdapter.PORTRAIT_TYPE, this)
    }

    private val ratingAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(objectViewType = MovieItemAdapter.PORTRAIT_TYPE, this)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setAdapterToRv()
        setupClickers()
        observeRatingMovies()
        observePopularMovies()
        observeRelevancyMovies()
        observePublishedAtMovies()
    }


    private fun setAdapterToRv() {
        requireBinding().popularMovieRecViewMoviesFragment.adapter = popularAdapter
        requireBinding().upcomingMovieRecViewMoviesFragment.adapter = upcomingAdapter
        requireBinding().nowPlayingMovieRecViewMoviesFragment.adapter = publishedAtAdapter
        requireBinding().topRatedMovieRecViewMoviesFragment.adapter = ratingAdapter
    }

    private fun observeRatingMovies() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
            requireBinding().shimmerLayout.visibility = View.INVISIBLE
            requireBinding().noConnection.visibility = View.VISIBLE
        }

        lifecycleScope.launchWhenResumed {
            viewModel.ratingMovies.collectLatest {
                ratingAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePublishedAtMovies() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
            requireBinding().shimmerLayout.visibility = View.INVISIBLE
            requireBinding().noConnection.visibility = View.VISIBLE
        }

        lifecycleScope.launchWhenResumed {
            viewModel.publishedAtMovies.collectLatest {
                publishedAtAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePopularMovies() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
            requireBinding().shimmerLayout.visibility = View.INVISIBLE
            requireBinding().noConnection.visibility = View.VISIBLE
        }


        lifecycleScope.launchWhenResumed {
            viewModel.popularMovies.collectLatest {
                popularAdapter.submitList(it.movies)
                requireBinding().allConst.visibility = View.VISIBLE
                requireBinding().shimmerLayout.visibility = View.INVISIBLE
            }
        }
    }

    private fun observeRelevancyMovies() {
        viewModel.error.onEach {
            makeToast(it, requireContext())
            requireBinding().shimmerLayout.visibility = View.INVISIBLE
            requireBinding().noConnection.visibility = View.VISIBLE
        }

        lifecycleScope.launchWhenResumed {
            viewModel.relevanceMovies.collectLatest {
                upcomingAdapter.submitList(it.movies)
            }
        }

    }

    private fun setupClickers() = with(requireBinding()) {
        popular_MovieSeeAll_movieFrag.setOnClickListener {

            launchToSeeAllFragment(MovieType.POPULAR)
        }
        topRated_MovieSeeAll_movieFrag.setOnClickListener {
            launchToSeeAllFragment(MovieType.TOP_RATED)
        }
        now_playing_MovieSeeAll_movieFrag.setOnClickListener {
            launchToSeeAllFragment(MovieType.NOW_PLAYING)
        }
        upcoming_MovieSeeAll_movieFrag.setOnClickListener {
            launchToSeeAllFragment(MovieType.UPCOMING)
        }
    }

    private fun launchToSeeAllFragment(type: MovieType) = viewModel.goMoreMovieFragment(type)

    @SuppressLint("CutPasteId")
    fun openDialogSheet(item: MovieUi) {
        val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.alert_item_for_movie_det)
        val movieImage = bottomSheet.findViewById<ImageView>(R.id.profile_picture)
        val movieDes = bottomSheet.findViewById<TextView>(R.id.overview_text)
        val movieMore = bottomSheet.findViewById<MaterialButton>(R.id.play_button)
        val movieTitle = bottomSheet.findViewById<TextView>(R.id.name)
        val cancelBtn = bottomSheet.findViewById<ImageView>(R.id.close_btn)
        val rel = bottomSheet.findViewById<TextView>(R.id.releaseDate)
        val prof_img = bottomSheet.findViewById<ImageView>(R.id.profile_picture)
        val vote = bottomSheet.findViewById<AppCompatRatingBar>(R.id.voteAverage_item_cast)
        cancelBtn?.setOnClickListener { bottomSheet.dismiss() }
        Picasso.get().load(Utils.POSTER_PATH_URL + item.backdropPath).into(movieImage)
        movieDes?.text = item.overview
        movieTitle?.text = item.title
        rel?.text = item.releaseDate
        vote?.rating = item.voteCount!!.toFloat()
        Picasso.get().load(Utils.POSTER_PATH_URL + item.posterPath).into(prof_img)
        movieMore?.setOnClickListener {
            bottomSheet.dismiss()
            viewModel.goMovieDetails(item)
        }
        bottomSheet.setCancelable(true)
        bottomSheet.show()

    }

    override fun onLongClick(item: MovieUi) {
        openDialogSheet(item)
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovie(item)
        makeToast("${item.title} saved successfully", requireContext())
    }

    override fun onReady(savedInstanceState: Bundle?) {}

}

