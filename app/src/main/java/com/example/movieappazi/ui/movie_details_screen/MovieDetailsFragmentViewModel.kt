package com.example.movieappazi.ui.movie_details_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import com.example.domain.iteractors.movie_iteractors.GetFavoriteMovieUseCase
import com.example.movieappazi.base.BaseViewModel
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
    private val resourceProvider: ResourceProvider,
    private val saveMovieRepository: MovieStorageRepository,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val _movieId: MutableLiveData<Int> = MutableLiveData()

    private val _setFavImageRecourse: MutableSharedFlow<Boolean> = MutableSharedFlow()
    val setFavImageRecourse: SharedFlow<Boolean> = _setFavImageRecourse

    //    private val _isFavorite: MutableSharedFlow<Boolean> = MutableSharedFlow()
//    val isFavorite: SharedFlow<Boolean> = _isFavorite
    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
    val isFavorite: LiveData<Boolean> = _isFavorite
//    private val _isFavorite: MutableLiveData<Boolean> = MutableLiveData()
//    val isFavorite: LiveData<Boolean> = _isFavorite
//
//    private val _setFavImageRecourse: MutableLiveData<Boolean> = MutableLiveData()
//    val setFavImageRecourse: LiveData<Boolean> = _setFavImageRecourse


    private val movieIdFlow = MutableStateFlow(movieId)
    private val personsIds = MutableStateFlow(actorsIds)

    val movieFlow =
        movieIdFlow.map(movieRepository::getMovieDetails).map { it.map(mapMovieDetails) }

    val castFLow =
        movieIdFlow.map(movieRepository::getActors).map { it.map(mapCreditsResponseDomain) }


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


    fun setMovieId(id: Int) {
        _movieId.value = id
        chekIsFavorite()
    }

    private fun chekIsFavorite() = viewModelScope.launch {
        val movie = getFavoriteMovieUseCase.execute(id = _movieId.value!!)
        _isFavorite.value = movie != null
        movieIdFlow.map(movieRepository::getMovieDetails).map { it.map(mapMovieDetails) }
    }

    fun saveMovieFromRv(movieUi: MovieUi) = viewModelScope.launch {
        _setFavImageRecourse.tryEmit(false)
        saveMovieRepository.saveMovieToDatabase(movie = mapFromUiToDomain.map(movieUi))
    }

    fun deleteMovieFromRv(movieId: Int) = viewModelScope.launch {
        _setFavImageRecourse.tryEmit(true)
        saveMovieRepository.deleteMovieFromDatabase(movieId = movieId)
    }

    fun goBack() = navigateBack()

}