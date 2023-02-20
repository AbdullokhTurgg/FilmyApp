package com.example.movieappazi.ui.movie.search_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.models.movie.MovieDomain
import com.example.domain.models.movie.MoviesDomain
import com.example.domain.repositories.network.movie.MovieRepositories
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.domain.state.takeSuccess
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.MovieUi
import com.example.movieappazi.app.models.movie.MoviesUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import com.example.movieappazi.app.utils.extensions.createMutableSharedFlowAsLiveData
import com.example.movieappazi.ui.movie.search_movies_screen.router.FragmentSearchMoviesRouter
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesFragmentViewModel @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val saveMapper: BaseMapper<MovieUi, MovieDomain>,
    private val mapMoviesResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val repository: MovieRepositories,
    private val resourceProvider: HandleExeption,
) : BaseViewModel() {

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()
    val moviesFlow = createMutableSharedFlowAsLiveData<MoviesUi>()


    fun searchMovie(keyword: String) = repository.getSearchMovies(query = keyword)
        .map(mapMoviesResponse::map)
        .flowOn(Dispatchers.Default)
        .catch { error: Throwable -> _error.emit(resourceProvider.hanEx(error)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)

    fun saveMovie(movie: MovieUi) =
        viewModelScope.launch { storageRepository.saveMovieToDatabase(saveMapper.map(movie)) }

    fun launchMovieDetails(movie: MovieUi) =
        navigation(SearchMoviesFragmentDirections.actionNavSearchToMovieDetailsFragment(movie))

}