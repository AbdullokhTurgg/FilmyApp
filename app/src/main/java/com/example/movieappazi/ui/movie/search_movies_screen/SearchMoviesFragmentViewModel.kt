package com.example.movieappazi.ui.movie.search_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.helper.DispatchersProvider
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesDomain
import com.example.domain.models.movie.tv_shows.SeriesDomain
import com.example.domain.models.movie.tv_shows.TvSeriesResponseDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import com.example.movieappazi.app.models.movie.tv_shows.SeriesUi
import com.example.movieappazi.app.models.movie.tv_shows.TvSeriesResponseUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.ui.movie.see_all_movies_screen.MovieType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesFragmentViewModel @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val saveMovieMapper: BaseMapper<MovieUi, MovieDomain>,
    private val saveTvMapper:BaseMapper<SeriesUi, SeriesDomain>,
    private val mapMoviesResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val mapSeriesResponse:BaseMapper<TvSeriesResponseDomain, TvSeriesResponseUi>,
    private val repository: MovieRepositories,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: HandleExeption,
) : BaseViewModel() {

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()


    fun searchMovie(keyword: String) = repository.getSearchMovies(query = keyword)
        .map(mapMoviesResponse::map)
        .flowOn(dispatchersProvider.default())
        .catch { error: Throwable -> _error.emit(resourceProvider.hanEx(error)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun searchSeries(keyword: String) = repository.getAllSearchSeries(query = keyword)
        .map(mapSeriesResponse::map)
        .flowOn(dispatchersProvider.default())
        .catch { error: Throwable -> _error.emit(resourceProvider.hanEx(error)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun goSeeMore(type: MovieType) = navigation(SearchMoviesFragmentDirections.actionNavSearchToSeeAllMoviesFragment(type))

    fun saveMovie(movie: MovieUi) =
        viewModelScope.launch { storageRepository.saveMovieToDatabase(saveMovieMapper.map(movie)) }

    fun launchMovieDetails(movie: MovieUi) =
        navigation(SearchMoviesFragmentDirections.actionNavSearchToMovieDetailsFragment(movie.id!!))

    fun saveTv(series:SeriesUi) =
        viewModelScope.launch { storageRepository.tvSave(saveTvMapper.map(series)) }

    fun launchTvDetails(series: SeriesUi) =
        navigation(SearchMoviesFragmentDirections.actionNavSearchToSeriesDetailsFragment(series.id))

}