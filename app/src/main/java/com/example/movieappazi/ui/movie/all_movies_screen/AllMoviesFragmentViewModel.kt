package com.example.movieappazi.ui.movie.all_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.HomeScreenItems
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.domain.usecases.FetchAllHomeScreenItemsUseCase
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import com.example.movieappazi.app.models.movie.ResponseState
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.app.utils.extensions.changeResponseState
import com.example.movieappazi.ui.movie.all_movies_screen.listeners.MovieItemOnClickListener
import com.example.movieappazi.ui.movie.all_movies_screen.mappers.MainItemsToSearchFilteredModelMapper
import com.example.movieappazi.ui.movie.all_movies_screen.router.FragmentAllMoviesRouter
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import com.example.movieappazi.ui.movie.see_all_movies_screen.SeeAllMoviesFragmentDirections
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class AllMoviesFragmentViewModel @Inject constructor(
    private val fetchAllHomeScreenItemsUseCase: FetchAllHomeScreenItemsUseCase,
    private val repository: MovieRepositories,
    private val storageRepository: MovieStorageRepository,
    private val mapFromMoviesDomainToUi: BaseMapper<MoviesDomain, MoviesUi>,
    private val dispatchersProvider: DispatchersProvider,
    private val mapper: BaseMapper<MovieUi, MovieDomain>,
    private val hanEx: HandleExeption,
    private val fragmentAllMoviesRouter: FragmentAllMoviesRouter,
    private val itemsToSearchFilteredModelMapper: MainItemsToSearchFilteredModelMapper,
) : BaseViewModel(), MovieItemOnClickListener {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()
    private val pageToResponseFlow = MutableStateFlow(_movieResponseState.value.page)


    val allFilteredMovieItems = fetchAllHomeScreenItemsUseCase()
        .map { items -> mapToAdapterModel(items) }
        .onStart {}
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> hanEx.hanEx(exception) }
        .stateIn(viewModelScope, SharingStarted.Lazily, null)


    val popularMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getPopularMovie(it) }
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> hanEx.hanEx(exception) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val relevanceMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getUpcomingMovies(it) }
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> hanEx.hanEx(exception) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val fancyMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getUpcomingMovies(it) }
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> hanEx.hanEx(exception) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val publishedAtMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getNowPlayingMovies(it) }
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> hanEx.hanEx(exception) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val ratingMoviesFlow = pageToResponseFlow
        .flatMapLatest {repository.getTopRatedMovies(it)}
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> hanEx.hanEx(exception) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val featuredMoviesFlow = pageToResponseFlow
        .flatMapLatest { repository.getPopularMovie(it)}
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { exception: Throwable -> hanEx.hanEx(exception) }
        .onEach { value -> settings(value.page, value.totalPage) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun goMoreMovieFragment(type: MovieType) =
        navigation(AllMoviesFragmentDirections.actionNavMoviesToSeeAllMoviesFragment(type))

    fun goMovieDetails(moviesUi: MovieUi) =
        navigation(AllMoviesFragmentDirections.actionNavMoviesToMovieDetailsFragment2(moviesUi))

    fun goMovieDetailsFromSeeMore(movieUi: MovieUi) =
        navigation(SeeAllMoviesFragmentDirections.actionSeeAllMoviesFragmentToMovieDetailsFragment(movieUi))

    fun mapToAdapterModel(items: HomeScreenItems) =
        itemsToSearchFilteredModelMapper.map(items, this)

    fun nextPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)

    fun previousPage() = pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)

    fun settings(page: Int, totalPage: Int) = viewModelScope.launch {
        _movieResponseState.emit(changeResponseState(page = page, totalPage = totalPage)) }

    fun saveMovie(moviesUi: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(mapper.map(moviesUi)) }


    override fun movieItemOnClick(movieUi: MovieUi) {
        navigate(fragmentAllMoviesRouter.navigateToMovieDetailsFragment(movieUi))
    }
}


