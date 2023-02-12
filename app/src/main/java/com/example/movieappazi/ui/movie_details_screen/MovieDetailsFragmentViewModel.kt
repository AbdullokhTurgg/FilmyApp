package com.example.movieappazi.ui.movie_details_screen

import androidx.lifecycle.viewModelScope
import com.example.data.network.cloud.base.ResourceProvider
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.domainRepositories.network.movie.MovieRepositories
import com.example.domain.domainRepositories.storage.MovieStorageRepository
import com.example.movieappazi.base.BaseViewModel
import com.example.movieappazi.exception.HandleExeption
import com.example.movieappazi.uiModels.movie.CreditsResponseUi
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.movie.MoviesUi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

@ExperimentalCoroutinesApi
class MovieDetailsFragmentViewModel constructor(
    private val movieId: Int,
    private val actorsIds: List<Int>,
    private val movieRepository: MovieRepositories,
    private val mapMovieDetails: BaseMapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapCreditsResponseDomain: BaseMapper<CreditsResponseDomain, CreditsResponseUi>,
    private val mapMovieResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val mapFromUiToDomain: BaseMapper<MovieUi, MovieDomain>,
    private val dispatchersProvider: DispatchersProvider,
    private val mapFromMoviesDomainToUi: BaseMapper<MoviesDomain, MoviesUi>,
    private val resourceProvider: ResourceProvider,
    private val saveMovieRepository: MovieStorageRepository,
    ) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()


    private val movieIdFlow = MutableStateFlow(movieId)
    private val personsIds = MutableStateFlow(actorsIds)

    val movieFlow =
        movieIdFlow.map(movieRepository::getMovieDetails).map { it.map(mapMovieDetails) }

    val castFLow =
        movieIdFlow.map(movieRepository::getActors).map { it.map(mapCreditsResponseDomain) }

    val presenMovies = movieRepository.getNowPlayingMovies(9).map(mapFromMoviesDomainToUi::map)
        .flowOn(dispatchersProvider.default()).catch { t: Throwable ->
            _error.emit(resourceProvider.handleException(t))
        }.shareIn(viewModelScope, SharingStarted.Lazily, 1)


    val similarMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getSimilarMovies(it)
    }.map(mapMovieResponse::map).flowOn(dispatchersProvider.default()).onEach {
        it.movies.forEach {}
    }.catch { throwable: Throwable ->
        _error.emit(resourceProvider.handleException(throwable = throwable))
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getRecommendMovies(it)
    }.map(mapMovieResponse::map).flowOn(dispatchersProvider.default()).onEach {
        it.movies.forEach {}
    }.catch { throwable: Throwable ->
        _error.emit(resourceProvider.handleException(throwable = throwable))
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun saveMovieFromRv(movieUi: MovieUi) = viewModelScope.launch {
        saveMovieRepository.saveMovieToDatabase(movie = mapFromUiToDomain.map(movieUi))
    }

    fun deleteMovieFromRv(movieId: Int) = viewModelScope.launch {
        saveMovieRepository.deleteMovieFromDatabase(movieId = movieId)
    }

    fun goBack() = navigateBack()

}