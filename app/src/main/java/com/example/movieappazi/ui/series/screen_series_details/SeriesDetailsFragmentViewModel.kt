package com.example.movieappazi.ui.series.screen_series_details

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesDetailsDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.CastUi
import com.example.movieappazi.app.models.movie.CreditsResponseUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesDetailsUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesResponseUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class SeriesDetailsFragmentViewModel constructor(
    private val tvId: Int,
    private val movieRepository: MovieRepositories,
    private val storageRepository: MovieStorageRepository,
    private val mapMovieDetailsDomainToUi: BaseMapper<TvSeriesDetailsDomain, TvSeriesDetailsUi>,
    private val mapTvSeriesResponseDomainToUi: BaseMapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: BaseMapper<SeriesUi, SeriesDomain>,
    private val mapCreditsResponseDomain: BaseMapper<CreditsResponseDomain, CreditsResponseUi>,
    private val resourceProvider: HandleExeption,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val tvFlow = MutableStateFlow(tvId)

    fun goCastInfo(castUi: CastUi) = navigation(SeriesDetailsFragmentDirections.actionSeriesDetailsFragmentToActorsDetailsFragment(castUi.id))
    fun saveTv(tv: SeriesUi) = viewModelScope.launch { storageRepository.tvSave(saveMapper.map(tv)) }
    fun goBack() = navigateBack()
    fun nextDetails(tv: SeriesUi) { navigation(SeriesDetailsFragmentDirections.actionSeriesDetailsFragmentSelf(tv.id)) }

    val tvCastFlow = movieRepository.getTvCasts(tvId = tvId)
        .map(mapCreditsResponseDomain::map)
        .flowOn(Dispatchers.Default)
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val movieFlow = tvFlow
        .flatMapLatest { movieRepository.getTvSeriesDetails(it) }
        .map(mapMovieDetailsDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val similarMoviesFlow = tvFlow
        .flatMapLatest { movieRepository.getTvSimilar(it) }
        .map(mapTvSeriesResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = tvFlow
        .flatMapLatest { movieRepository.getTvRecommendations(it) }
        .map(mapTvSeriesResponseDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

}
