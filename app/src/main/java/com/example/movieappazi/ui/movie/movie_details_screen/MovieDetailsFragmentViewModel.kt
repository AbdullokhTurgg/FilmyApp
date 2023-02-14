package com.example.movieappazi.ui.movie.movie_details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.helper.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.CreditsResponseUi
import com.example.movieappazi.app.models.movie.MovieDetailsUi
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import com.example.movieappazi.app.utils.exception.HandleExeption
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
    private val resourceProvider: HandleExeption,
    private val saveMovieRepository: MovieStorageRepository,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val movieIdFlow = MutableStateFlow(movieId)


    val movieFlow =
        movieIdFlow.map(movieRepository::getMovieDetails).map { it.map(mapMovieDetails) }

    val castFLow =
        movieIdFlow.map(movieRepository::getActors).map { it.map(mapCreditsResponseDomain) }


    val similarMoviesFlow = movieIdFlow.flatMapLatest { movieRepository.getSimilarMovies(it)
    }.map(mapMovieResponse::map).flowOn(dispatchersProvider.default()).onEach { it.movies.forEach {}
    }.catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t))
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow.flatMapLatest { movieRepository.getRecommendMovies(it)
    }.map(mapMovieResponse::map).flowOn(dispatchersProvider.default()).onEach { it.movies.forEach {}
    }.catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t))
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)


    fun saveMovieFromRv(movieUi: MovieUi) = viewModelScope.launch {
        saveMovieRepository.saveMovieToDatabase(movie = mapFromUiToDomain.map(movieUi))
    }

}