package com.example.movieappazi.ui.movie_details_screen

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.data.network.api.utils.Utils
import com.example.domain.state.DataRequestState
import com.example.movieappazi.R
import com.example.movieappazi.base.BaseFragment
import com.example.movieappazi.databinding.FragmentMovieDetailsBinding
import com.example.movieappazi.extensions.hideView
import com.example.movieappazi.extensions.launchWhenViewStarted
import com.example.movieappazi.extensions.makeToast
import com.example.movieappazi.ui.adapters.movie.adapter_for_popular.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener_for_adapters.RvClickListener
import com.example.movieappazi.ui.adapters.person.PersonDetailsAdapter
import com.example.movieappazi.uiModels.movie.CastUi
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.utils.blur_effect.BlurTransformation
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.include_movie_info_block.*
import kotlinx.android.synthetic.main.include_movie_info_block.view.*
import kotlinx.android.synthetic.main.include_movie_info_poster_block.*
import kotlinx.android.synthetic.main.include_movie_info_toolbar.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsFragmentViewModel>(FragmentMovieDetailsBinding::inflate), RvClickListener<MovieUi>,
    PersonDetailsAdapter.RvClickListener {

    private val movieId: Int by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.id!!
    }
    private val actorsIds: List<Int> by lazy {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.genre_ids!!
    }

    @Inject
    lateinit var viewModelFactory: MovieDetailsFragmentViewModelFactory.Factory
    @OptIn(ExperimentalCoroutinesApi::class)
    override val viewModel by viewModels<MovieDetailsFragmentViewModel> {
        viewModelFactory.create(movieId = movieId, actorsIds = actorsIds)
    }

    private val similarMoviesAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this@MovieDetailsFragment)
    }
    private val reccomendAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.PORTRAIT_TYPE, this@MovieDetailsFragment)
    }
    private val presentAdapter: MovieItemAdapter by lazy {
        MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this@MovieDetailsFragment)
    }
    private val personAdapter: PersonDetailsAdapter by lazy {
        PersonDetailsAdapter(this@MovieDetailsFragment)
    }
    private val moviee: MovieUi by lazy(LazyThreadSafetyMode.NONE) {
        MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie
    }


    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickers()
        setAdaptersToRv()
        observeMovieDetails()
        observeData()
    }

    private fun setAdaptersToRv() = with(include_movie_info_block) {
        similarMoviesRv.adapter = similarMoviesAdapter
        reccomendRv.adapter = reccomendAdapter
        actorsRv.adapter = personAdapter
        presentMovies.adapter = presentAdapter
    }

    private fun observeData() = with(viewModel) {
        launchWhenViewStarted {
            similarMoviesFlow.observe { similarMoviesAdapter.submitList(it.movies) }
            recommendMoviesFlow.observe { reccomendAdapter.submitList(it.movies) }
            presenMovies.observe { presentAdapter.submitList(it.movies) }
        }
    }

    private fun setupClickers() = with(include_movie_info_toolbar_block) {
        back_icon.setOnClickListener {
            viewModel.goBack()
        }
        save_icon.setOnClickListener {
            viewModel.saveMovieFromRv(moviee)
            makeToast("${moviee.title} Saved", requireContext())
        }
    }

    private fun observeMovieDetails() {
        lifecycleScope.launchWhenStarted {
            viewModel.movieFlow.collectLatest {
                when (it) {
                    is DataRequestState.Success -> {
                        setMovieUi(it.data)
                    }
                    is DataRequestState.Error -> {
                        makeToast(it.error.message.toString(), requireContext())
                    }
                }
            }
        }
        lifecycleScope.launchWhenStarted {
            viewModel.castFLow.collectLatest {
                when (it) {
                    is DataRequestState.Success -> {
                        personAdapter.personsList = it.data.cast
                    }

                    is DataRequestState.Error -> {
                        makeToast(it.error.message.toString(), requireContext())
                    }
                }
            }
        }
    }


    private fun setMovieUi(movie: MovieDetailsUi) = with(requireBinding()) {
        includeMovieInfoToolbarBlock.apply {
            toolbarMovieTitle.text = movie.title
        }

        includeMovieInfoPosterBlock.apply {
            Glide.with(requireContext()).asBitmap().load(Utils.POSTER_PATH_URL + movie.posterPath)
            Picasso.get().load(Utils.POSTER_PATH_URL + movie.posterPath).into(bookPoster)
            bookTitle.text = movie.title
            bookAuthor.text = movie.originalTitle
        }

        includeMovieInfoBlock.apply {
            moviePublicYear.text = movie.releaseDate
            movieLanguage.text = movie.originalLanguage
            moviePopularity.text = movie.voteCount.toString()
            bookSubtitle.text = movie.overview
            movieBudgetTxt.text = movie.budget.toString()
            movieVoteTxt.text = movie.voteCount.toString()
            voteAverage.rating = movie.voteAverage.toFloat()
            movieStatusTxt.text = movie.status
        }
    }

    override fun onItemClick(item: MovieUi) {
        viewModel.saveMovieFromRv(item)
        makeToast("${item.title} saved successful", requireContext())
    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(
            item))
    }

    override fun onPersonItemClick(person: CastUi) {
        @SuppressLint("CutPasteId") val bottomSheet = BottomSheetDialog(requireContext())
        bottomSheet.setContentView(R.layout.bottom_cast)
        val castImage = bottomSheet.findViewById<ImageView>(R.id.profile_picture)
        val castName = bottomSheet.findViewById<TextView>(R.id.name)
        val closeBtn = bottomSheet.findViewById<ImageView>(R.id.close_btn)
        val popularity = bottomSheet.findViewById<TextView>(R.id.popularity_txt)
        val gender = bottomSheet.findViewById<TextView>(R.id.gender_name_txt)
        val originName = bottomSheet.findViewById<TextView>(R.id.origin_name_txt)
        Picasso.get().load(Utils.POSTER_PATH_URL + person.profilePath).into(castImage)
        castName?.text = person.name
        popularity?.text = person.popularity.toString()
        gender?.text = person.knownForDepartment
        originName?.text = person.originalName
        closeBtn?.setOnClickListener {
            bottomSheet.dismissWithAnimation
            bottomSheet.dismiss()
        }
        bottomSheet.setCancelable(true)
        bottomSheet.show()

    }

    override fun onReady(savedInstanceState: Bundle?) {}

}