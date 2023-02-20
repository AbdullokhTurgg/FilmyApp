package com.example.movieappazi.ui.series.screen_all_series

import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.ResponseState
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesResponseUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.app.utils.extensions.changeResponseState
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllSeriesFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@Parcelize
enum class SeeAllTvType : Parcelable {
    POPULAR, TOP_RATED, AIRINGTODAY, ONTHEAIR, TRENDING, FAMILYTYPE, ANIMETYPE, DRAMATYPE, COMEDY, HISTORY, MYSTERY, WESTERN
}
@HiltViewModel
class AllSeriesFragmentViewModel @Inject constructor(
    private val repository: MovieRepositories,
//    private val storageRepository: MovieStorageRepository,
    private val mapTvResponse: BaseMapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val saveMapper: BaseMapper<SeriesUi, SeriesDomain>,
    private val resourceProvider: HandleExeption,
) : BaseViewModel() {

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()

    private val pageToResponseFlow = MutableStateFlow(_movieResponseState.value.page)

            val tvTrendingFlow = pageToResponseFlow
            .flatMapLatest { repository.getTrendingTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val tvTopRatedFlow = pageToResponseFlow
            .flatMapLatest { repository.getTopRatedTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val tvOnTheAirFlow = pageToResponseFlow
            .flatMapLatest { repository.getOnTheAirTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val tvPopularFlow = pageToResponseFlow
            .flatMapLatest { repository.getPopularTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val tvAiringTodayFlow = pageToResponseFlow
            .flatMapLatest { repository.getAiringTodayTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)


           val animeFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasyMovies(it, ANIMATION)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val familyMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasyMovies(it, FAMILY)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val comedyMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasyMovies(it, COMEDY)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val historyMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasyMovies(it, HISTORY)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val mysteryMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasyMovies(it, MYSTERY)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val westernMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasyMovies(it, WESTERN)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val dramaMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasyMovies(it, DRAMA)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)



    fun launchTvType(tv: SeeAllTvType) =
        navigation(AllSeriesFragmentDirections.actionNavTvShowsToSeeAllSeriesFragment(tv))

    fun goTvInfo(tv: SeriesUi) =
        navigation(AllSeriesFragmentDirections.actionNavTvShowsToSeriesDetailsFragment(tv))

    fun goTvInfoFromSeeMore(tv: SeriesUi) =
        navigation(SeeAllSeriesFragmentDirections.actionSeeAllSeriesFragmentToSeriesDetailsFragment(tv))

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    private fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage))
    }

    companion object {
        const val FAMILY = "80"
        const val ANIMATION = "35"
        const val COMEDY = "99"
        const val HISTORY = "10749"
        const val MYSTERY = "9648"
        const val WESTERN = "37"
        const val DRAMA = "18"
    }
}
