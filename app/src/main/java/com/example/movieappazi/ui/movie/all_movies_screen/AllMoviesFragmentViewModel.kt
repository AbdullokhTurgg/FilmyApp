package com.example.movieappazi.ui.movie.all_movies_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.domain.helper.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import com.example.movieappazi.app.models.movie.ResponseState
import com.example.movieappazi.ui.adapters.movie.listener.RvClickListener
import com.example.movieappazi.ui.movie.all_movies_screen.listeners.MovieItemOnClickListener
import com.example.movieappazi.ui.movie.all_movies_screen.router.FragmentAllMoviesRouter
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
    private val fragmentAllMoviesRouter: FragmentAllMoviesRouter,
) : BaseViewModel(), MovieItemOnClickListener {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieResponseState = MutableStateFlow(ResponseState())
    val movieResponseState get() = _movieResponseState.asStateFlow()

    private val movieCategoryFlow = MutableStateFlow(MovieType.POPULAR)

    private val pageToResponseFlow = MutableStateFlow(value = _movieResponseState.value.page)

    private val categoryAndPageFlow =
        movieCategoryFlow.combine(pageToResponseFlow) { category, page ->
            Pair(category, page)
        }


    val popularMoviesFlow = repository.getPopularMovie(1).map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val relevanceMoviesFlow = repository.getUpcomingMovies(1)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default()).catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val fancyMoviesFlow = repository.getUpcomingMovies(13)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val publishedAtMoviesFlow = repository.getNowPlayingMovies(1)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default()).catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val ratingMoviesFlow = repository.getTopRatedMovies(1)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default()).catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun goMoreMovieFragment(type: MovieType) =
        navigate(fragmentAllMoviesRouter.navigateToSeeMoreFragment(type = type))

    fun goMovieDetails(movie: MovieUi) =
        navigate(fragmentAllMoviesRouter.navigateToMovieDetailsFragment(movie))

    fun saveMovie(moviesUi: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(mapper.map(moviesUi))
    }

    override fun movieItemOnClick(movieUi: MovieUi) {
        navigate(fragmentAllMoviesRouter.navigateToMovieDetailsFragment(movieUi))
    }


}


