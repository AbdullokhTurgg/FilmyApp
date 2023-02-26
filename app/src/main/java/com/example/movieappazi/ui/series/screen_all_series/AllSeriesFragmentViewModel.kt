package com.example.movieappazi.ui.series.screen_all_series

import android.os.Parcelable
import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.ResponseState
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesResponseUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.app.utils.extensions.changeResponseState
import com.example.movieappazi.ui.movie.all_movies_screen.AllMoviesFragmentDirections
import com.example.movieappazi.ui.movie.search_movies_screen.SearchType
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllSeriesFragmentDirections
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllTvType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.android.parcel.Parcelize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllSeriesFragmentViewModel @Inject constructor(
    private val repository: MovieRepositories,
    private val storageRepository: MovieStorageRepository,
    private val saveMapper: BaseMapper<SeriesUi, SeriesDomain>,
    private val mapTvResponse: BaseMapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val resourceProvider: HandleExeption,
) : BaseViewModel() {


    fun saveTv(tv: SeriesUi) = viewModelScope
        .launch { storageRepository.tvSave(saveMapper.map(tv)) }

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()

    private val pageToResponseFlow = MutableStateFlow(_movieResponseState.value.page)

            @OptIn(ExperimentalCoroutinesApi::class)
            val tvTrendingFlow = pageToResponseFlow
            .flatMapLatest { repository.getTrendingTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           @OptIn(ExperimentalCoroutinesApi::class)
           val tvTopRatedFlow = pageToResponseFlow
            .flatMapLatest { repository.getTopRatedTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           @OptIn(ExperimentalCoroutinesApi::class)
           val tvOnTheAirFlow = pageToResponseFlow
            .flatMapLatest { repository.getOnTheAirTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           @OptIn(ExperimentalCoroutinesApi::class)
           val tvPopularFlow = pageToResponseFlow
            .flatMapLatest { repository.getPopularTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           @OptIn(ExperimentalCoroutinesApi::class)
           val tvAiringTodayFlow = pageToResponseFlow
            .flatMapLatest { repository.getAiringTodayTvSeries(it)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)


           @OptIn(ExperimentalCoroutinesApi::class)
           val animeFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasySeries(it, ANIMATIONTV)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           @OptIn(ExperimentalCoroutinesApi::class)
           val familyMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasySeries(it, FAMILYTV)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           @OptIn(ExperimentalCoroutinesApi::class)
           val comedyMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasySeries(it, COMEDYTV)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val historyMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasySeries(it, HISTORY)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val mysteryMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasySeries(it, MYSTERYTV)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

           val westernMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasySeries(it, WESTERNTV)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val dramaMoviesFlow = pageToResponseFlow
            .flatMapLatest { repository.getFantasySeries(it, DRAMATV)
            .map(mapTvResponse::map) }
            .flowOn(Dispatchers.Default)
            .onEach { value -> settings(value.page, value.total_pages) }
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val crimeSeriesFlow = pageToResponseFlow
                .flatMapLatest { repository.getFantasySeries(it, CRIMETV)
                .map(mapTvResponse::map)}
                .flowOn(Dispatchers.Default)
                .onEach { value -> settings(value.page, value.total_pages) }
                .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
                .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val documentarySeriesFlow = pageToResponseFlow
               .flatMapLatest { repository.getFantasySeries(it, DOCUMENTARYTV)
               .map(mapTvResponse::map)}
               .flowOn(Dispatchers.Default)
               .onEach { value -> settings(value.page, value.total_pages) }
               .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
               .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val kidsSeriesFlow = pageToResponseFlow
                .flatMapLatest { repository.getFantasySeries(it, KIDS)
                .map(mapTvResponse::map)}
                .flowOn(Dispatchers.Default)
                .onEach { value -> settings(value.page, value.total_pages) }
                .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
                .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val newsSeriesFlow = pageToResponseFlow
                .flatMapLatest { repository.getFantasySeries(it, NEWS)
                .map(mapTvResponse::map)}
                .flowOn(Dispatchers.Default)
                .onEach { value -> settings(value.page, value.total_pages) }
                .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
                .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val realitySeriesFlow = pageToResponseFlow
                .flatMapLatest { repository.getFantasySeries(it, REALITY)
                .map(mapTvResponse::map)}
                .flowOn(Dispatchers.Default)
                .onEach { value -> settings(value.page, value.total_pages) }
                .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
                .shareIn(viewModelScope, SharingStarted.Lazily, 1)

            val soapSeriesFlow = pageToResponseFlow
                .flatMapLatest { repository.getFantasySeries(it, SOAP)
                .map(mapTvResponse::map)}
                .flowOn(Dispatchers.Default)
                .onEach { value -> settings(value.page, value.total_pages) }
                .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
                .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun goSearchMovie(type: SearchType) =
        navigation(AllSeriesFragmentDirections.actionNavTvShowsToNavSearch(type))

    fun launchTvType(tv: SeeAllTvType) =
        navigation(AllSeriesFragmentDirections.actionNavTvShowsToSeeAllSeriesFragment(tv))

    fun goTvInfo(tv: SeriesUi) =
        navigation(AllSeriesFragmentDirections.actionNavTvShowsToSeriesDetailsFragment(tv.id))

    fun goTvInfoFromSeeMore(tv: SeriesUi) =
        navigation(SeeAllSeriesFragmentDirections.actionSeeAllSeriesFragmentToSeriesDetailsFragment(tv.id))

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    private fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage))
    }

    companion object {
        const val ACTION   =    "28"
        const val ADVENTURE    =   "12"
        const val COMEDY     =    " 35"
        const val CRIME      =     "80"
        const val DOCUMENTARY =    "99"
        const val DRAMA       =    "18"
        const val FAMILY      =    "10751"
        const val FANTASY      =   "14"
        const val HISTORY    =     "36"
        const val HORROR     =     "27"
        const val MUSIC      =     "10402"
        const val MYSTERY    =     "9648"
        const val ROMANCE    =     "10749"
        const val SCIENCEFICTION= "878"
        const val THRILLER  =      "53"
        const val WAR       =      "10752"
        const val WESTERN   =      "37"

        // Series
        const val ACTIONANDADVENTURE = "10759"
        const val ANIMATIONTV    =       "16"
        const val COMEDYTV      =        "35"
        const val CRIMETV       =        "80"
        const val DOCUMENTARYTV  =       "99"
        const val DRAMATV        =       "18"
        const val FAMILYTV       =       "10751"
        const val KIDS         =       "10762"
        const val MYSTERYTV     =        "9648"
        const val NEWS        =        "10763"
        const val REALITY     =        "10764"
        const val SCIENCEFANTASY =   "10765"
        const val SOAP          =      "10766"
        const val TALK         =       "10767"
        const val WARPOLITICS   =   "10768"
        const val WESTERNTV       =      "37"
    }
}
