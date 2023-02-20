package com.example.movieappazi.ui.movie.see_all_movies_screen

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import android.widget.ScrollView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.recyclerview.animations.AddableItemAnimator
import com.example.movieappazi.app.recyclerview.animations.custom.SlideBackCommonAnimator
import com.example.movieappazi.app.recyclerview.animations.custom.SlideInLeftCommonAnimator
import com.example.movieappazi.app.recyclerview.animations.custom.SlideInTopCommonAnimator
import com.example.movieappazi.app.utils.extensions.*
import com.example.movieappazi.app.utils.motion.MotionListener
import com.example.movieappazi.app.utils.motion.MotionState
import com.example.movieappazi.databinding.FragmentSeeAllSeriesBinding
import com.example.movieappazi.ui.movie.movie_details_screen.MovieDetailsFragment
import com.example.movieappazi.ui.movie.movie_details_screen.MovieDetailsFragment.Companion.NOTYET
import com.example.movieappazi.ui.series.adapter.TvAdapter
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel
import com.example.movieappazi.ui.series.screen_all_series.SeeAllTvType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class SeeAllSeriesFragment : BaseFragment<FragmentSeeAllSeriesBinding, AllSeriesFragmentViewModel>(
    FragmentSeeAllSeriesBinding::inflate), TvAdapter.RecyclerOnClickListener {

    private val args by navArgs<SeeAllSeriesFragmentArgs>()
    override val viewModel: AllSeriesFragmentViewModel by viewModels()
    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onItemClick(movie: SeriesUi) = viewModel.goTvInfoFromSeeMore(movie)
    override fun onLongItemClick(movie: SeriesUi) {showErrorSnackbar(PRESSONCE)}

    private val trendingRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val topRatedRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val onTheAirRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val popularRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val airingRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val familyRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val dramaRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val animeRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val comedRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val historyRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val mysteryRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val westernRv by lazy { TvAdapter(TvAdapter.POPULAR_TYPE, this) }
    private val motionListener = MotionListener(::setToolbarState)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = TransitionInflater.from(requireContext())
        enterTransition = inflater.inflateTransition(R.transition.slide_up)
        exitTransition = inflater.inflateTransition(R.transition.right)
    }

    override fun onStart() {
        super.onStart()
        requireBinding().root.progress = viewModel.motionPosition.value
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).hideView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        observeType(args.tvtype)
        setupClickers()
        nextAnim()
    }

    private fun observeTopRatedTv() = with(viewModel) {
        launchWhenViewStarted { tvTopRatedFlow.observe { topRatedRv.seriesList = it.series
            visibilities() } } }

    private fun observeAiringTv() = with(viewModel) {
        launchWhenViewStarted { tvAiringTodayFlow.observe { airingRv.seriesList = it.series
            visibilities() } } }

    private fun observeOnTheAirTv() = with(viewModel) {
        launchWhenViewStarted { tvOnTheAirFlow.observe { onTheAirRv.seriesList = it.series
            visibilities() } } }

    private fun observePopularTv() = with(viewModel) {
        launchWhenViewStarted { tvPopularFlow.observe { popularRv.seriesList = it.series
            visibilities() } } }

    private fun observeTrendingTv() = with(viewModel) {
        launchWhenViewStarted { tvTrendingFlow.observe { trendingRv.seriesList = it.series
            visibilities() } } }

    private fun observeFamilyTv() = with(viewModel) {
        launchWhenViewStarted { familyMoviesFlow.observe { familyRv.seriesList = it.series
            visibilities() } } }

    private fun observeAnimeTv() = with(viewModel) {
        launchWhenViewStarted { animeFlow.observe { animeRv.seriesList = it.series
            visibilities() } } }

    private fun observeDramaTv() = with(viewModel) {
        launchWhenViewStarted { dramaMoviesFlow.observe { dramaRv.seriesList = it.series
            visibilities()} } }

    private fun observeComedyTv() = with(viewModel) {
        launchWhenViewStarted { comedyMoviesFlow.observe { comedRv.seriesList = it.series
            visibilities()} } }

    private fun observeHistoryTv() = with(viewModel) {
        launchWhenViewStarted { historyMoviesFlow.observe { historyRv.seriesList = it.series
            visibilities()} } }

    private fun observeWesternTv() = with(viewModel) {
        launchWhenViewStarted { westernMoviesFlow.observe { westernRv.seriesList = it.series
            visibilities()} } }

    private fun observeMysteryTv() = with(viewModel) {
        launchWhenViewStarted { mysteryMoviesFlow.observe { mysteryRv.seriesList = it.series
            visibilities()} } }

    private fun observeType(tv: SeeAllTvType) = with(requireBinding()) {
        when (tv) {
            SeeAllTvType.TOP_RATED -> {
                observeTopRatedTv()
                moviesRv.adapter = topRatedRv
                title.text = TOPRATED
                img.setImageResource(R.drawable.topratedimg)
            }
            SeeAllTvType.AIRINGTODAY -> {
                observeAiringTv()
                moviesRv.adapter = airingRv
                title.text = AIRINGTODAY
                img.setImageResource(R.drawable.airingtoday)
            }
            SeeAllTvType.ONTHEAIR -> {
                observeOnTheAirTv()
                moviesRv.adapter = onTheAirRv
                title.text = ONTHEAIR
                img.setImageResource(R.drawable.ontheairimg)
            }
            SeeAllTvType.POPULAR -> {
                observePopularTv()
                moviesRv.adapter = popularRv
                title.text = POPULAR
                img.setImageResource(R.drawable.popularimg)
            }
            SeeAllTvType.TRENDING -> {
                observeTrendingTv()
                moviesRv.adapter = trendingRv
                title.text = TRENDING
                img.setImageResource(R.drawable.trendingimg)
            }
            SeeAllTvType.FAMILYTYPE -> {
                observeFamilyTv()
                moviesRv.adapter = familyRv
                title.text = FAMILIY
            }
            SeeAllTvType.ANIMETYPE -> {
                observeAnimeTv()
                moviesRv.adapter = animeRv
                title.text = ANIME

            }
            SeeAllTvType.DRAMATYPE -> {
                observeDramaTv()
                moviesRv.adapter = dramaRv
                title.text = DRAMA

            }
            SeeAllTvType.COMEDY -> {
                observeComedyTv()
                moviesRv.adapter = comedRv
                title.text = COMEDY

            }
            SeeAllTvType.HISTORY -> {
                observeHistoryTv()
                moviesRv.adapter = historyRv
                title.text = HISTORY

            }
            SeeAllTvType.WESTERN -> {
                observeWesternTv()
                moviesRv.adapter = westernRv
                title.text = WESTERN

            }
            SeeAllTvType.MYSTERY -> {
                observeMysteryTv()
                moviesRv.adapter = mysteryRv
                title.text = MYSTERY

            }
        }
        observeBtns()
    }

    private fun FragmentSeeAllSeriesBinding.observeBtns() =
        lifecycleScope.launchWhenResumed {
            viewModel.movieResponseState.collectLatest { state ->
                prevPageText.text = state.previousPage.toString()
                currentPageText.text = state.page.toString()
                nextPageText.text = state.nextPage.toString()
                prevBtn.apply {
                    isClickable = state.isHasPreviousPage
                    isFocusable = state.isHasPreviousPage
                }
                nextBtn.apply {
                    isClickable = state.isHasNextPage
                    isFocusable = state.isHasNextPage
                }
            }
        }

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
    private fun setupClickers() = with(requireBinding()) {
        upButton.setOnDownEffectClickListener { viewModel.navigateBack() }

        viewModel.apply {
            nextBtn.setOnDownEffectClickListener {nextPage()
                nextPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP) }

            prevBtn.setOnDownEffectClickListener {prevAnim()
                previousPage()
                scrollView.fullScroll(ScrollView.FOCUS_UP)
            }
        }

    }

    private fun nextAnim() = with(requireBinding().moviesRv) {
        itemAnimator = AddableItemAnimator(

            SlideInTopCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_item_portrait,
                SlideInTopCommonAnimator())

            anim.addDuration = BaseFragment.DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = BaseFragment.DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }

    private fun prevAnim() = with(requireBinding().moviesRv) {
        itemAnimator = AddableItemAnimator(

            SlideBackCommonAnimator()).also { anim ->
            anim.addViewTypeAnimation(
                R.layout.object_item_portrait,
                SlideBackCommonAnimator())

            anim.addDuration = BaseFragment.DEFAULT_ITEMS_ANIMATOR_DURATION
            anim.removeDuration = BaseFragment.DEFAULT_ITEMS_ANIMATOR_DURATION
        }
    }


    private fun visibilities() = with(requireBinding()){
        pageConstraint.showView()
        isEmptyLoading.hideView()
    }

    companion object {
        const val COLLAPSED = 1f
        const val EXPANDED = 0f
        const val DEFAULT_ITEMS_ANIMATOR_DURATION = 500L
        const val POPULAR = "Popular"
        const val TOPRATED = "Top Rated"
        const val ONTHEAIR = "On The Air"
        const val AIRINGTODAY = "Airing Today"
        const val TRENDING = "Trending"
        const val FAMILIY = "Family"
        const val ANIME = "Anime"
        const val DRAMA = "Drama"
        const val COMEDY = "Comedy"
        const val HISTORY = "History"
        const val WESTERN = "Western"
        const val MYSTERY = "Mystery"
        const val SOMETHINGWENTWRONG = "Something went wrong while processing"
        const val PRESSONCE = "Press Once"
    }
}

