package com.example.movieappazi.ui.movie.see_all_movies_screen

import android.os.Bundle
import android.os.Parcelable
import android.transition.TransitionInflater
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.recyclerview.animations.AddableItemAnimator
import com.example.movieappazi.app.recyclerview.animations.custom.SlideBackCommonAnimator
import com.example.movieappazi.app.recyclerview.animations.custom.SlideInTopCommonAnimator
import com.example.movieappazi.app.utils.extensions.*
import com.example.movieappazi.app.utils.motion.MotionListener
import com.example.movieappazi.app.utils.motion.MotionState
import com.example.movieappazi.databinding.FragmentSeeMoreBinding
import com.example.movieappazi.ui.adapters.movie.MovieItemAdapter
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragmentViewModel
import com.example.movieappazi.ui.movie.movie_details_screen.MovieDetailsFragment.Companion.NOTYET
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@Parcelize
enum class MovieType : Parcelable {
    POPULAR, TOP_RATED, NOW_PLAYING, UPCOMING }

@OptIn(ExperimentalCoroutinesApi::class)
@AndroidEntryPoint
class SeeAllMoviesFragment :
    BaseFragment<FragmentSeeMoreBinding, AllMoviesFragmentViewModel>(FragmentSeeMoreBinding::inflate),
    RvClickListener<MovieUi> {

    @OptIn(ExperimentalCoroutinesApi::class)
    override val viewModel: AllMoviesFragmentViewModel by viewModels()
    override fun onReady(savedInstanceState: Bundle?) {}

    private val args by navArgs<SeeAllMoviesFragmentArgs>()

    override fun onItemClick(item: MovieUi) { viewModel.saveMovie(item)
        showSuccessSnackBar("${item.title} saved successfully") }

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun onLongClick(item: MovieUi) { viewModel.goMovieDetailsFromSeeMore(item) }

    private val ratingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this) }
    private val popularAdapter by lazy { MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this) }
    private val upcomingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this) }
    private val nowPlayingAdapter by lazy { MovieItemAdapter(MovieItemAdapter.POPULAR_TYPE, this) }
    private val motionListener = MotionListener(::setToolbarState)




    private fun setupViews() = with(requireBinding()){
        root.addTransitionListener(motionListener)
    }


    private fun setToolbarState(state: MotionState) {
        when (state) {
            MotionState.COLLAPSED ->{
                viewModel.updateMotionPosition(BaseFragment.COLLAPSED)
                requireBinding().scrollView.smoothScrollTo(0,0)
            }

            MotionState.EXPANDED -> viewModel.updateMotionPosition(BaseFragment.EXPANDED)
            else -> Unit
        }
    }
    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_up)
        exitTransition = inflater.inflateTransition(R.transition.right)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        nextAnim()
        setupViews()
        setupTypes()
        setupClickers()
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun setupClickers() = with(requireBinding()) {
        upButton.setOnDownEffectClickListener { viewModel.navigateBack() }
        sortOptions.setOnDownEffectClickListener { launchWhenViewStarted { delay(1000)
            showWarningSnackbar(NOTYET) }}

        viewModel.apply {
            nextBtnMovie.setOnDownEffectClickListener {
                nextPage()
                nextAnim()
                scrollView.fullScroll(ScrollView.FOCUS_UP) }

            prevBtnMovie.setOnDownEffectClickListener {
                previousPage()
                prevAnim()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }

    }

    private fun visibilities() = with(requireBinding()){
        pageConstraint.showView()
        isEmptyLoading.hideView()
    }

    private fun setupTypes() = with(requireBinding()) {
        when (args.type) {
            MovieType.TOP_RATED -> {
                moviesRv.adapter = ratingAdapter
                title.text = TOPRATED
                img.setImageResource(R.drawable.topratedimg)
                observeAllTopRated()
            }
            MovieType.UPCOMING -> {
                moviesRv.adapter = upcomingAdapter
                title.text = UPCOMING
                img.setImageResource(R.drawable.upcomingimg)
                observeAllUpcoming()
            }
            MovieType.POPULAR -> {
                moviesRv.adapter = popularAdapter
                title.text = POPULAR
                observeAllPopular()
            }
            MovieType.NOW_PLAYING -> {
                moviesRv.adapter = nowPlayingAdapter
                title.text = NOWPLAYING
                img.setImageResource(R.drawable.nowplayingimg)
                observeAllNowPlaying()
            }
        }
        observeBtns()
    }


    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeAllNowPlaying() = with(viewModel) {
        launchWhenViewStarted { publishedAtMoviesFlow.observe { nowPlayingAdapter.submitList(it.movies)
            visibilities() } } }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeAllUpcoming() = with(viewModel) {
        launchWhenViewStarted { relevanceMoviesFlow.observe { upcomingAdapter.submitList(it.movies)
            visibilities() } } }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeAllPopular() = with(viewModel) {
        launchWhenViewStarted { popularMoviesFlow.observe { popularAdapter.submitList(it.movies)
            visibilities() } } }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun observeAllTopRated() = with(viewModel) {
        launchWhenViewStarted { ratingMoviesFlow.observe {ratingAdapter.submitList(it.movies)
            visibilities() } } }


    private fun FragmentSeeMoreBinding.observeBtns() =
        lifecycleScope.launchWhenResumed {
            viewModel.movieResponseState.collectLatest { state ->
                prevPageText.text = state.previousPage.toString()
                currentPageText.text = state.page.toString()
                nextPageText.text = state.nextPage.toString()

                prevBtnMovie.apply {
                    isClickable = state.isHasPreviousPage
                    isFocusable = state.isHasPreviousPage
                }
                nextBtnMovie.apply {
                    isClickable = state.isHasNextPage
                    isFocusable = state.isHasNextPage
                }
            }
        }

    private fun nextAnim() = with(requireBinding().moviesRv) {
        itemAnimator = AddableItemAnimator(

            SlideInTopCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_item_portrait,
                SlideInTopCommonAnimator())

            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    private fun prevAnim() = with(requireBinding().moviesRv) {
        itemAnimator = AddableItemAnimator(

            SlideBackCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_item_portrait,
                SlideBackCommonAnimator())

            anim.addDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    companion object{
        const val POPULAR = "Popular"
        const val TOPRATED = "Top Rated"
        const val NOWPLAYING = "Now Playing"
        const val UPCOMING = "Upcoming"
        const val FANCY = "Fancy"
        const val FEATURED = "Featured"
    }
}