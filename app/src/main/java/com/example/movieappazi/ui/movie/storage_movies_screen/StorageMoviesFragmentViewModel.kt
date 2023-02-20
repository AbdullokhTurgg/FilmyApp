package com.example.movieappazi.ui.movie.storage_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.ui.movie.storage_movies_screen.router.FragmentStorageRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StorageMoviesFragmentViewModel @Inject constructor(
    private val savedRepository: MovieStorageRepository,
    private val mapFromListMovieDomainToUi: BaseMapper<List<MovieDomain>, List<MovieUi>>,
    private val mapSeriesDomainToUi: BaseMapper<List<SeriesDomain>, List<SeriesUi>>,
    private val resourceProvider: HandleExeption,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    fun deleteMovies(movies: Int) = viewModelScope.launch {
        savedRepository.deleteMovieFromDatabase(movieId = movies) }

    fun deleteTV(tvId: Int) = viewModelScope.launch { savedRepository.tvDelete(tvId = tvId) }

    fun launchMovieDetails(movie: MovieUi) =
        navigation(StorageMoviesFragmentDirections.actionNavStorageToMovieDetailsFragment(movie))

    fun launchTvDetails(seriesUi: SeriesUi) =
        navigation(StorageMoviesFragmentDirections.actionNavStorageToSeriesDetailsFragment(seriesUi))


    val storageMoviesFlow = savedRepository.getAllMoviesFromDatabase()
        .map(mapFromListMovieDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    val tvStorageFlow = savedRepository.tvGetStorage()
        .map(mapSeriesDomainToUi::map)
        .flowOn(Dispatchers.Default)
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

}
