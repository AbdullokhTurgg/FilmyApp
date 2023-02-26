package com.example.movieappazi.ui.series.screen_all_series

import android.os.Bundle
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
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllTvType
import com.example.movieappazi.ui.adapters.tv.TvAdapter
import com.example.movieappazi.ui.movie.search_movies_screen.SearchType
import com.google.android.material.bottomnavigation.BottomNavigationView
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllSeriesFragment :
    BaseFragment<FragmentAllSeriesBinding, AllSeriesFragmentViewModel>(FragmentAllSeriesBinding::inflate),
    TvAdapter.RecyclerOnClickListener {

    override val viewModel: AllSeriesFragmentViewModel by viewModels()
    override fun onReady(savedInstanceState: Bundle?) {}

    override fun onItemClick(movie: SeriesUi) =
        try {viewModel.goTvInfo(movie) }
        catch (e:Exception){showErrorSnackbar(e.toString())}

    override fun onLongItemClick(movie: SeriesUi) {
        try { viewModel.saveTv(movie)
            showSuccessSnackBar(" Сериал ${movie.originalName} успешно сохранен!") }
        catch (e:Exception){showErrorSnackbar(e.message.toString())}
    }

    private val trendingRv by lazy { TvAdapter(TvAdapter.GENRES_MAIN_ITEM, this) }
    private val topRatedRv by lazy { TvAdapter(TvAdapter.PORTRAIT_TYPE, this) }
    private val onTheAirRv by lazy { TvAdapter(TvAdapter.GENRES_MAIN_ITEM, this) }
    private val popularRv by lazy { TvAdapter(TvAdapter.GENRES_MAIN_ITEM, this) }
    private val airingTodayRv by lazy { TvAdapter(TvAdapter.GENRES_MAIN_ITEM, this) }
    private val familyMoviesAdapter by lazy { TvAdapter(TvAdapter.GENRES_MAIN_ITEM, this) }
    private val animeMoviesAdapter by lazy { TvAdapter(TvAdapter.GENRES_MAIN_ITEM, this) }

    private fun setupClickers() = with(requireBinding()) {
        viewModel.apply {
            upcomingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.TRENDING) }
            topText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.ONTHEAIR) }
            popularText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.POPULAR) }
            trendingText.setOnDownEffectClickListener { launchTvType(SeeAllTvType.AIRINGTODAY) }
            westernGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.WESTERN) }
            comedyGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.COMEDY) }
            dramaGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.DRAMATYPE) }
            mysteryGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.MYSTERY) }
            historyGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.HISTORY) }
            crimeGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.CRYME) }
            documentaryGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.DOCUMENTARY) }
            kidsGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.KIDS) }
            newsGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.NEWS) }
            realityGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.REALITY) }
            soapGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.SOAP) }
            familyGenre.setOnDownEffectClickListener { launchTvType(SeeAllTvType.FAMILYTYPE) }
            goSearchSeriesBtn.setOnDownEffectClickListener{goSearchMovie(SearchType.TV)}
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
                visibility()
            }
        }
    }

    private fun visibility() = with(requireBinding()) {
        shimmerLayout.hideView()
        scrollMain.showView()
    }
}
