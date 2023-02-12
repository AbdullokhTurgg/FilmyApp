package com.example.movieappazi.ui.storage_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.data.network.cloud.base.ResourceProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainRepositories.storage.MovieStorageRepository
import com.example.movieappazi.base.BaseViewModel
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StorageMoviesFragmentViewModel @Inject constructor(
    private val savedRepository: MovieStorageRepository,
    private val mapFromListMovieDomainToUi: BaseMapper<List<MovieDomain>, List<MovieUi>>,
    private val resourceProvider: ResourceProvider,
    ) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    val storageMovies = savedRepository.getAllMoviesFromDatabase()
        .map(mapFromListMovieDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { throwable: Throwable -> _error.emit(resourceProvider.handleException(throwable = throwable)) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteMovies(movies: Int) = viewModelScope.launch {
        savedRepository.deleteMovieFromDatabase(movieId = movies)
    }

    fun launchMovieDetails(list: MovieUi) =
        navigate(StorageMoviesFragmentDirections.actionNavStorageToMovieDetailsFragment(list))
}