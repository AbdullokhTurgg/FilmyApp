package com.example.movieappazi.ui.all_movies_screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.data.network.retrofit.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.databinding.FragmentAllMoviesBinding
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.root_fragment.StudentViewPagerAdapter
import com.example.movieappazi.ui.see_all_movies_screen.MovieType
import com.example.movieappazi.ui.zAdapter.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.zAdapter.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.uiModels.movie.MovieUi
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.button.MaterialButton
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.alert_item_for_movie_det.*
import kotlinx.android.synthetic.main.fragment_all_movies.*
import kotlinx.android.synthetic.main.fragment_root.*
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.supervisorScope

@AndroidEntryPoint
class AllMoviesFragment : Fragment(), RvClickListener<MovieUi> {

    private val binding by lazy {
        FragmentAllMoviesBinding.inflate(layoutInflater)
    }
    private val viewModel: AllMoviesFragmentViewModel by viewModels()

    private val popularAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this)
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

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupClickers()
        setAdapterToRv()
        observePopularMovies()
        observeRelevancyMovies()
        observePublishedAtMovies()
        observeRatingMovies()

    }

    private fun setAdapterToRv() {
        binding.popularMovieRecViewMoviesFragment.adapter = popularAdapter
        binding.upcomingMovieRecViewMoviesFragment.adapter = upcomingAdapter
        binding.nowPlayingMovieRecViewMoviesFragment.adapter = publishedAtAdapter
        binding.topRatedMovieRecViewMoviesFragment.adapter = ratingAdapter
    }

    private fun observeRatingMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.ratingMovies.collectLatest {
                ratingAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePublishedAtMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.publishedAtMovies.collectLatest {
                publishedAtAdapter.submitList(it.movies)
            }
        }
    }

    private fun observePopularMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.popularMovies.collectLatest {
                popularAdapter.submitList(it.movies)
                binding.allConst.visibility = View.VISIBLE
                binding.shimmerLayout.visibility = View.GONE
            }
        }
    }

    private fun observeRelevancyMovies() {
        lifecycleScope.launchWhenResumed {
            viewModel.relevanceMovies.collectLatest {
                upcomingAdapter.submitList(it.movies)
            }
        }

    }

    private fun setupClickers() = with(binding) {
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

    private fun launchToSeeAllFragment(type: MovieType) {
        findNavController().navigate(AllMoviesFragmentDirections.actionNavMoviesToSeeAllMoviesFragment(
            type))
    }

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
        val vote = bottomSheet.findViewById<AppCompatRatingBar>(R.id.voteAverage_item)
        cancelBtn?.setOnClickListener { bottomSheet.dismiss() }
        Picasso.get().load(Utils.POSTER_PATH_URL + item.backdropPath).into(movieImage)
        movieDes?.text = item.overview
        movieTitle?.text = item.title
        rel?.text = item.releaseDate
        vote?.rating = item.voteCount.toFloat()
        Picasso.get().load(Utils.POSTER_PATH_URL + item.posterPath).into(prof_img)
        movieMore?.setOnClickListener {
            findNavController().navigate(AllMoviesFragmentDirections.actionAllMoviesFragmentToMovieDetailsFragment(
                item))
            bottomSheet.dismiss()
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
}

