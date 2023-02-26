package com.example.movieappazi.ui.series.screen_series_details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesDetailsDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.models.movie.CreditsResponseUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesDetailsUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesResponseUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject


private const val TV_ID_KEY = "tv_id_key"

class TvDetailsViewModelFactory @AssistedInject constructor(
    @Assisted(TV_ID_KEY) private val tvId: Int,
    private val movieRepository: MovieRepositories,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: BaseMapper<TvSeriesDetailsDomain, TvSeriesDetailsUi>,
    private val mapTvSeriesResponseDomainToUi: BaseMapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: BaseMapper<SeriesUi, SeriesDomain>,
    private val resourceProvider: HandleExeption,
    private val mapCreditsResponseDomain: BaseMapper<CreditsResponseDomain, CreditsResponseUi>,
    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == SeriesDetailsFragmentViewModel::class.java)
        return SeriesDetailsFragmentViewModel(
            tvId = tvId,
            storageRepository = storageRepository,
            mapMovieDetailsDomainToUi = mapMovieDetailsDomainToUi,
            movieRepository = movieRepository,
            mapTvSeriesResponseDomainToUi = mapTvSeriesResponseDomainToUi,
            saveMapper = saveMapper,
            resourceProvider = resourceProvider,
            mapCreditsResponseDomain=mapCreditsResponseDomain
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(TV_ID_KEY) tvId: Int,
        ): TvDetailsViewModelFactory
    }
}
