package com.example.movieappazi.ui.series.screen_all_series

import android.os.Bundle
import android.transition.TransitionInflater
import android.view.View
import androidx.fragment.app.viewModels
import com.example.movieappazi.R
import com.example.movieappazi.app.base.BaseFragment
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.utils.extensions.hideView
import com.example.movieappazi.app.utils.extensions.launchWhenViewStarted
import com.example.movieappazi.app.utils.extensions.setOnDownEffectClickListener
import com.example.movieappazi.app.utils.extensions.showView
import com.example.movieappazi.databinding.FragmentAllSeriesBinding
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllSeriesFragment.Companion.PRESSONCE
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllSeriesFragment.Companion.SOMETHINGWENTWRONG
import com.example.movieappazi.ui.series.adapter.TvAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllSeriesFragment :
    BaseFragment<FragmentAllSeriesBinding, AllSeriesFragmentViewModel>(FragmentAllSeriesBinding::inflate),
    TvAdapter.RecyclerOnClickListener {

    override val viewModel: AllSeriesFragmentViewModel by viewModels()
    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onItemClick(movie: SeriesUi) = viewModel.goTvInfo(movie)
    override fun onLongItemClick(movie: SeriesUi) {
        showErrorSnackbar(PRESSONCE)
    }

    private val trendingRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val topRatedRv by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val onTheAirRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val popularRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val airingTodayRv by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val familyMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }
    private val animeMoviesAdapter by lazy { TvAdapter(TvAdapter.HORIZONTAL_TYPE, this) }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            upcomingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.TRENDING) }
            topText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.ONTHEAIR) }
            popularText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.POPULAR) }
            trendingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.AIRINGTODAY) }
            animeText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.ANIMETYPE) }
            westernGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.WESTERN) }
            comedyGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.COMEDY) }
            dramaGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.DRAMATYPE) }
            documentGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.MYSTERY) }
            historyGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.HISTORY) }
            familyGenre.setOnDownEffectClickListener{ launchTvType(SeeAllTvType.FAMILYTYPE)}
        }
    }

    override fun onStart() {
        super.onStart()
        requireActivity().findViewById<BottomNavigationView>(R.id.bottomNavMenu2).showView()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setAdapterToRv()
        observeViewModel()
        setupClickers()
    }

    private fun setAdapterToRv() = with(requireBinding()) {
        popSeries.adapter = topRatedRv
        tvTrendingRv.adapter = trendingRv
        tvTopretedRv.adapter = topRatedRv
        tvOnTheAirRv.adapter = onTheAirRv
        tvPopularRv.adapter = familyMoviesAdapter
        tvAiringTodayRv.adapter = animeMoviesAdapter
    }

    private fun observeViewModel() = with(viewModel) {
        launchWhenViewStarted {
            tvTopRatedFlow.observe { topRatedRv.seriesList = it.series }
            tvOnTheAirFlow.observe { onTheAirRv.seriesList = it.series }
            tvPopularFlow.observe { popularRv.seriesList = it.series }
            tvAiringTodayFlow.observe { airingTodayRv.seriesList = it.series }
            familyMoviesFlow.observe { familyMoviesAdapter.seriesList = it.series }
            animeFlow.observe { animeMoviesAdapter.seriesList = it.series }
            tvTrendingFlow.observe {
                trendingRv.seriesList = it.series
                uiVisibility()
            }
        }
    }

    private fun uiVisibility() = with(requireBinding()) {
        shimmerLayout.hideView()
        scrollMain.showView()
    }
}
