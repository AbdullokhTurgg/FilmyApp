package com.example.movieappazi.ui.movie.movie_details_screen

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.data.cloud.api.utils.Utils
import com.example.domain.state.DataRequestState
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.CastUi
import com.example.movieappazi.app.models.movie.MovieDetailsUi
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.utils.extensions.*
import com.example.movieappazi.app.utils.motion.MotionListener
import com.example.movieappazi.app.utils.motion.MotionState
import com.example.movieappazi.databinding.FragmentMovieDetailsBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter.Companion.PORTRAIT_TYPE
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.adapters.person.PersonDetailsAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_movie_details.*
import kotlinx.android.synthetic.main.include_movie_info_block.*
import kotlinx.android.synthetic.main.include_movie_info_block.view.*
import kotlinx.android.synthetic.main.include_movie_info_poster_block.*
import kotlinx.android.synthetic.main.include_movie_info_toolbar.*
import kotlinx.android.synthetic.main.include_movie_info_toolbar.view.*
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Inject

@OptIn(ExperimentalCoroutinesApi::class)
@DelicateCoroutinesApi
@AndroidEntryPoint
class MovieDetailsFragment :
    BaseFragment<FragmentMovieDetailsBinding, MovieDetailsFragmentViewModel>(
        FragmentMovieDetailsBinding::inflate), RvClickListener<MovieUi>,
    PersonDetailsAdapter.RvClickListener {

    private val similarMoviesAdapter by lazy { MovieItemAdapter(PORTRAIT_TYPE, this) }
    private val reccomendAdapter by lazy { MovieItemAdapter(PORTRAIT_TYPE, this) }
    private val personAdapter by lazy { PersonDetailsAdapter(this) }

    private val moviee: MovieUi by lazy { MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie }
    private val movieId: Int by lazy { MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.id!! }
    private val actorsIds: List<Int> by lazy { MovieDetailsFragmentArgs.fromBundle(requireArguments()).movie.genre_ids!! }

    @Inject
    lateinit var viewModelFactory: MovieDetailsFragmentViewModelFactory.Factory
    @OptIn(ExperimentalCoroutinesApi::class)
    override val viewModel by viewModels<MovieDetailsFragmentViewModel> {
        viewModelFactory.create(movieId = movieId, actorsIds = actorsIds)
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
        setupClickers()
        setAdaptersToRv()
        observeMovieDetails()
        observeData()
    }

    private fun setAdaptersToRv() = with(include_movie_info_block) {
        similarMoviesRv.adapter = similarMoviesAdapter
        reccomendRv.adapter = reccomendAdapter
        actorsRv.adapter = personAdapter
    }

    private fun observeData() = with(viewModel) {
        launchWhenViewStarted {
            similarMoviesFlow.observe { similarMoviesAdapter.submitList(it.movies) }
            recommendMoviesFlow.observe { reccomendAdapter.submitList(it.movies) }
        }
    }
    private fun setupViews() = with(requireBinding()){
        root.addTransitionListener(motionListener)
    }

    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED ->{
                viewModel.updateMotionPosition(COLLAPSED)
                requireBinding().includeMovieInfoBlock.nestedScroolView.smoothScrollTo(0,0)
            }

            MotionState.EXPANDED -> viewModel.updateMotionPosition(EXPANDED)
            else -> Unit
        }
    }
    private fun setupClickers() = with(requireBinding()) {
        includeMovieInfoPosterBlock.backIcon.setOnDownEffectClickListener { viewModel.navigateBack() }
        includeMovieInfoToolbarBlock.backIcon.setOnDownEffectClickListener { viewModel.navigateBack() }
        includeMovieInfoToolbarBlock.saveIcon.setOnDownEffectClickListener{viewModel.saveMovieFromRv(moviee)
            showSuccessSnackBar("${moviee.title} Saved Successful")}
        includeMovieInfoPosterBlock.saveIcon.setOnDownEffectClickListener { viewModel.saveMovieFromRv(moviee)
            showSuccessSnackBar("${moviee.title} Saved Successful") }
    }

    private fun observeMovieDetails() = with(viewModel) {
        launchWhenViewStarted {
            movieFlow.observe {
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
        launchWhenViewStarted {
            castFLow.observe {
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
        includeMovieInfoToolbarBlock.apply { toolbarMovieTitle.text = movie.title }

        includeMovieInfoPosterBlock.apply {
            Glide.with(requireContext()).asBitmap().load(Utils.POSTER_PATH_URL + movie.posterPath)
            requireContext().showBlurImage(blurSize = BACKGROUND_IMAGE_BLUR_SIZE,
                imageUrl = Utils.POSTER_PATH_URL + movie.posterPath,
                imageView = requireBinding().includeMovieInfoPosterBlock.movieBlurBackgroundPoster)
            Picasso.get().load(Utils.POSTER_PATH_URL + movie.posterPath).into(bookPoster)
            bookTitle.text = movie.title
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
        showSuccessSnackBar("${item.title} saved successful")
    }

    companion object {
        const val BACKGROUND_IMAGE_BLUR_SIZE = 25f
        const val NOTYET = "Not yet ready to show"
    }

    override fun onLongClick(item: MovieUi) {
        findNavController().navigate(MovieDetailsFragmentDirections.actionMovieDetailsFragmentSelf(item))
    }

    override fun onReady(savedInstanceState: Bundle?) {}
    override fun onPersonItemClick(person: CastUi) {showWarningSnackbar(NOTYET)}

}