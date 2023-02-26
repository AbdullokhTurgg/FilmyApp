package com.example.movieappazi.ui.movie.movie_details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.helper.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.CreditsResponseDomain
import com.example.domain.models.movie.MovieDetailsDomain
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.*
import com.example.movieappazi.app.utils.exception.HandleExeption
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieDetailsFragmentViewModel constructor(
    private val movieId: Int,
    private val movieRepository: MovieRepositories,
    private val mapMovieDetails: BaseMapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapCreditsResponseDomain: BaseMapper<CreditsResponseDomain, CreditsResponseUi>,
    private val mapMovieResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val mapFromUiToDomain: BaseMapper<MovieUi, MovieDomain>,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: HandleExeption,
    private val saveMovieRepository: MovieStorageRepository,
) : BaseViewModel() {

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    fun saveMovieFromRv(movieUi: MovieUi) = viewModelScope.launch {
        saveMovieRepository.saveMovieToDatabase(movie = mapFromUiToDomain.map(movieUi)) }

    fun goCastInfo(cast:CastUi) =
        navigation(MovieDetailsFragmentDirections.actionMovieDetailsFragmentToActorsDetailsFragment(cast.id))

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val movieIdFlow = MutableStateFlow(movieId)

    val castFlow = movieRepository.getActors(movieId = movieId)
        .map(mapCreditsResponseDomain::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val movieDetailsFlow = movieRepository.getMovieDetails(movieId = movieId)
        .map(mapMovieDetails::map)
        .flowOn(dispatchersProvider.default())
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val similarMoviesFlow = movieIdFlow
        .flatMapLatest { movieRepository.getSimilarMovies(it) }
        .map(mapMovieResponse::map)
        .flowOn(dispatchersProvider.default())
        .onEach { it.movies.forEach {} }
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow
        .flatMapLatest { movieRepository.getRecommendMovies(it) }
        .map(mapMovieResponse::map)
        .flowOn(dispatchersProvider.default())
        .onEach { it.movies.forEach {} }
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

}