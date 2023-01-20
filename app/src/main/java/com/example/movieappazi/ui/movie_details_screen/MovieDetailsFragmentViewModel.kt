package com.example.movieappazi.ui.movie_details_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.data.network.cloud.base.ResourceProvider
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainRepositories.network.movie.MovieRepositories
import com.example.domain.iteractors.movie_iteractors.get_movie_actors_details_usecase.GetMovieActorsDetailsUseCase
import com.example.domain.state.takeSuccess
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MoviesUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import kotlinx.coroutines.flow.*

class MovieDetailsFragmentViewModel constructor(
    private val movieId: Int,
    private val actorsIds: List<Int>,
    private val movieRepository: MovieRepositories,
    private val mapMovieDetails: BaseMapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val mapPersons: BaseMapper<List<PersonDetailsDomain>, List<PersonDetailsUi>>,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: ResourceProvider,
    private val getMovieActorsDetailsUseCase: GetMovieActorsDetailsUseCase,
) : ViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val movieIdFlow = MutableStateFlow(movieId)
    private val personsIds = MutableStateFlow(actorsIds)

    val movieFlow =
        movieIdFlow.map(movieRepository::getMovieDetails).map { it.map(mapMovieDetails) }

//    val persons = personsIds.flatMapLatest(getMovieActorsUseCase::invoke)
//        .map { it.map { it.takeSuccess() }.filterNotNull() }
//        .map(mapPersons::map)

    val persons = personsIds.flatMapLatest(getMovieActorsDetailsUseCase::invoke)
        .map { it -> it.mapNotNull { it.takeSuccess() } }.onEach {
            it.forEach {
                Log.d("MY_LOG", it.name)
            }
        }.map(mapPersons::map)


    val similarMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getSimilarMovies(it)
    }.map(mapMovieResponse::map).flowOn(dispatchersProvider.default()).onEach {
        it.movies.forEach {
        }
    }.catch { throwable: Throwable ->
        _error.emit(resourceProvider.handleException(throwable = throwable))
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)

    val recommendMoviesFlow = movieIdFlow.flatMapLatest {
        movieRepository.getRecommendMovies(it)
    }.map(mapMovieResponse::map).flowOn(dispatchersProvider.default()).onEach {
        it.movies.forEach {
        }
    }.catch { throwable: Throwable ->
        _error.emit(resourceProvider.handleException(throwable = throwable))
    }.shareIn(viewModelScope, SharingStarted.Lazily, 1)


}