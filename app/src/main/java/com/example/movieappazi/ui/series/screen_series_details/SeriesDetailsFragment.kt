package com.example.movieappazi.ui.series.screen_series_details

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.data.cloud.api.utils.Utils
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesDetailsUi
import com.example.movieappazi.app.utils.extensions.*
import com.example.movieappazi.app.utils.motion.MotionListener
import com.example.movieappazi.app.utils.motion.MotionState
import com.example.movieappazi.databinding.FragmentSeriesDetailsBinding
import com.example.movieappazi.ui.movie.movie_details_screen.MovieDetailsFragment
import com.example.movieappazi.ui.movie.movie_details_screen.MovieDetailsFragmentArgs
import com.example.movieappazi.ui.series.adapter.TvAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class SeriesDetailsFragment :
    BaseFragment<FragmentSeriesDetailsBinding, SeriesDetailsFragmentViewModel>(
        FragmentSeriesDetailsBinding::inflate), TvAdapter.RecyclerOnClickListener {

    override fun onReady(savedInstanceState: Bundle?) {}

    private val tvId: Int by lazy { SeriesDetailsFragmentArgs.fromBundle(requireArguments()).tv.id }
    private val serie: SeriesUi by lazy { SeriesDetailsFragmentArgs.fromBundle(requireArguments()).tv }
    private val similarAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val recommendAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

    @Inject
    lateinit var viewModelFactory: TvDetailsViewModelFactory.Factory
    override val viewModel by viewModels<SeriesDetailsFragmentViewModel> {
        viewModelFactory.create(tvId = tvId)
    }
    private val motionListener = MotionListener(::setToolbarState)


    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_up)
        exitTransition = inflater.inflateTransition(R.transition.start)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observe()
        setAdaptersTv()

    }

    private fun setAdaptersTv() = with(requireBinding()) {
        includeMovieInfoBlock.apply {
            reccomendRv.adapter = recommendAdapter
            similarMoviesRv.adapter = similarAdapter
        }
    }

    private fun observe() = with(viewModel) {
        launchWhenViewStarted {
            movieFlow.observe(::setMovieUi)
            similarMoviesFlow.observe { similarAdapter.seriesList = it.series }
            recommendMoviesFlow.observe { recommendAdapter.seriesList = it.series }
        }

        error.onEach {
            makeToast(it, requireContext())
        }

    }

    private fun setupViews() = with(requireBinding()) {
        root.addTransitionListener(motionListener)
    }


    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED -> {
                viewModel.updateMotionPosition(COLLAPSED)
                requireBinding().includeMovieInfoBlock.nestedScroolView.smoothScrollTo(0, 0)
            }
            MotionState.EXPANDED -> viewModel.updateMotionPosition(EXPANDED)
            else -> Unit
        }
    }

    private fun setMovieUi(series: TvSeriesDetailsUi) = with(requireBinding()) {
        includeMovieInfoPosterBlock.saveIcon.setOnDownEffectClickListener {
            viewModel.saveTv(serie)
            showSuccessSnackBar("${series.name} Saved Successful")
        }
        includeMovieInfoToolbarBlock.saveIcon.setOnDownEffectClickListener {
            viewModel.saveTv(serie)
            showSuccessSnackBar("${series.name} Saved Successful")
        }

        includeMovieInfoToolbarBlock.apply { toolbarMovieTitle.text = series.originalName }

        includeMovieInfoPosterBlock.apply {
            Glide.with(requireContext()).asBitmap().load(Utils.POSTER_PATH_URL + series.posterPath)
            requireContext().showBlurImage(blurSize = MovieDetailsFragment.BACKGROUND_IMAGE_BLUR_SIZE,
                imageUrl = Utils.POSTER_PATH_URL + series.posterPath,
                imageView = requireBinding().includeMovieInfoPosterBlock.movieBlurBackgroundPoster)
            Picasso.get().load(Utils.POSTER_PATH_URL + series.posterPath).into(bookPoster)
            bookTitle.text = series.originalName
            backListeners()
        }

        includeMovieInfoBlock.apply {
            moviePublicYear.text = series.firstAirDate
            movieLanguage.text = series.originalLanguage
            moviePopularity.text = series.voteCount.toString()
            bookSubtitle.text = series.overview
            movieBudgetTxt.text = series.lastAirDate
            movieVoteTxt.text = series.voteCount.toString()
            voteAverage.rating = series.voteAverage.toFloat()
            movieStatusTxt.text = series.status
        }
    }

    private fun backListeners() = with(requireBinding()) {
        includeMovieInfoPosterBlock.backIcon.setOnDownEffectClickListener { viewModel.goBack() }
        includeMovieInfoToolbarBlock.backIcon.setOnDownEffectClickListener { viewModel.goBack() }
    }

    override fun onItemClick(item: SeriesUi) {
        viewModel.nextDetails(item)
    }

    override fun onLongItemClick(item: SeriesUi) {
        viewModel.saveTv(item)
        showSuccessSnackBar("${item.originalName} saved successfully")
    }

}