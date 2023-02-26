package com.example.movieappazi.ui.movie.all_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import com.example.movieappazi.app.models.movie.ResponseState
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.app.utils.extensions.changeResponseState
import com.example.movieappazi.ui.movie.search_movies_screen.SearchType
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllMoviesFragmentDirections
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.ACTION
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.ADVENTURE
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.COMEDY
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.CRIME
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.DOCUMENTARY
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.DRAMA
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.FAMILY
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.FANTASY
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.HISTORY
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.HORROR
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.MUSIC
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.MYSTERY
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.ROMANCE
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.SCIENCEFICTION
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.THRILLER
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.WAR
import com.example.movieappazi.ui.series.screen_all_series.AllSeriesFragmentViewModel.Companion.WESTERN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class AllMoviesFragmentViewModel @Inject constructor(
    private val repository: MovieRepositories,
    private val storageRepository: MovieStorageRepository,
    private val mapFromMoviesDomainToUi: BaseMapper<MoviesDomain, MoviesUi>,
    private val dispatchersProvider: DispatchersProvider,
    private val mapper: BaseMapper<MovieUi, MovieDomain>,
    private val hanEx: HandleExeption,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()
    private val pageToResponseFlow = MutableStateFlow(_movieResponseState.value.page)


    val popularMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getPopularMovie(it) }
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> _error.emit(hanEx.hanEx(exception)) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val upcomingMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getUpcomingMovies(it) }
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> _error.emit(hanEx.hanEx(exception)) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val publishedAtMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getNowPlayingMovies(it) }
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> _error.emit(hanEx.hanEx(exception)) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val ratingMoviesFlow = pageToResponseFlow
        .flatMapLatest {repository.getTopRatedMovies(it)}
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> _error.emit(hanEx.hanEx(exception)) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val comedyMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, COMEDY)
        .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { exception: Throwable -> _error.emit(hanEx.hanEx(exception)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val historyMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, HISTORY)
        .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val mysteryMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, MYSTERY)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val westernMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, WESTERN)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val dramaMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, DRAMA)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val familyMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, FAMILY)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val actionMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, ACTION)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val adventureMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, ADVENTURE)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val crimeMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, CRIME)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val documentaryMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, DOCUMENTARY)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val fantasyMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, FANTASY)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val horrorMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, HORROR)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val musicMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, MUSIC)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val romanceMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, ROMANCE)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val scienceFictionMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, SCIENCEFICTION)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val thrillerMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, THRILLER)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val warMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getFantasyMovies(it, WAR)
            .map(mapFromMoviesDomainToUi::map)}
        .flowOn(dispatchersProvider.default())
        .onEach { value -> settings(value.page, value.totalPage) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun goMoreMovieFragment(type: MovieType) =
        navigation(AllMoviesFragmentDirections.actionNavMoviesToSeeAllMoviesFragment(type))

    fun goMovieDetails(moviesUi: MovieUi) =
        navigation(AllMoviesFragmentDirections.actionNavMoviesToMovieDetailsFragment2(moviesUi.id!!))

    fun goSearchMovie(type:SearchType) =
        navigation(AllMoviesFragmentDirections.actionNavMoviesToNavSearch(type))

    fun goMovieDetailsFromSeeMore(movieUi: MovieUi) =
        navigation(SeeAllMoviesFragmentDirections.actionSeeAllMoviesFragmentToMovieDetailsFragment(movieUi.id!!))

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage)) }

    fun saveMovie(moviesUi: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(mapper.map(moviesUi)) }

}


