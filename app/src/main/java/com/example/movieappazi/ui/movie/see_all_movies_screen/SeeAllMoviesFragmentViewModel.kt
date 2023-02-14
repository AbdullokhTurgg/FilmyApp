package com.example.movieappazi.ui.movie.see_all_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.helper.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import com.example.movieappazi.app.models.movie.ResponseState
import com.example.movieappazi.ui.movie.see_all_movies_screen.router.FragmentSeeAllRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllMoviesFragmentViewModel @Inject constructor(
    private val repository: MovieRepositories,
    private val storageRepository: MovieStorageRepository,
    private val mapFromMoviesDomainToUi: BaseMapper<MoviesDomain, MoviesUi>,
    private val dispatchersProvider: DispatchersProvider,
    private val mapper: BaseMapper<MovieUi, MovieDomain>,
    private val hanEx: HandleExeption,
    private val fragmentSeeAllRouter: FragmentSeeAllRouter,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieResponseState = MutableStateFlow(ResponseState())

    private val pageToResponseFlow = MutableStateFlow(value = _movieResponseState.value.page)


    val allTopRatedMovies = repository.getTopRatedMovies(1)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val allPopularMovies = repository.getPopularMovie(1)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val allUpcomingMovies = repository.getUpcomingMovies(1)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val allNowPlayingMovies = repository.getNowPlayingMovies(1)
        .map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(hanEx.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun saveMovie(moviesUi: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(mapper.map(moviesUi))
    }

    fun goMovieDetailsFragment(movie: MovieUi) {
        navigate(fragmentSeeAllRouter.navigateToMovieDetailsFragment(movie = movie))
    }

    fun nextPage() {
        pageToResponseFlow.tryEmit(_movieResponseState.value.nextPage)
    }

    fun previousPage() {
        pageToResponseFlow.tryEmit(_movieResponseState.value.previousPage)
    }
}