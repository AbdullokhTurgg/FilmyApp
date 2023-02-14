package com.example.movieappazi.ui.movie.storage_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.repositories.storage.MovieStorageRepository
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.movie.MovieUi
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
    private val resourceProvider: HandleExeption,
    private val fragmentStorageRouter: FragmentStorageRouter,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    val storageMovies =
        savedRepository.getAllMoviesFromDatabase()
            .map(mapFromListMovieDomainToUi::map)
            .flowOn(Dispatchers.Default)
            .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
            .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())


    fun deleteMovies(movies: Int) = viewModelScope.launch {
        savedRepository.deleteMovieFromDatabase(movieId = movies)
    }

    fun launchMovieDetails(movie: MovieUi) =
        navigate(fragmentStorageRouter.navigateToDetailsFragment(movie))
}
