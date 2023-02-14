package com.example.movieappazi.ui.movie.search_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
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
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesFragmentViewModel @Inject constructor(
    private val storageRepository: MovieStorageRepository,
    private val saveMapper: BaseMapper<MovieUi, MovieDomain>,
    private val mapMoviesResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val repository: MovieRepositories,
    private val resourceProvider: HandleExeption,
    private val fragmentSearchMoviesRouter: FragmentSearchMoviesRouter,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    val movies = createMutableSharedFlowAsLiveData<MoviesUi>()

    fun searchMovie(query: String) = viewModelScope.launch {
        kotlin.runCatching {
            repository.searchMovie(query)
        }.onSuccess {
            if (it.takeSuccess() != null) {
                movies.emit(mapMoviesResponse.map(it.takeSuccess()!!))
            }
        }.onFailure {
            _error.emit(resourceProvider.hanEx(it))
        }
    }

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(movie = saveMapper.map(movie))
    }

   fun launchMovieDetails(movie: MovieUi) =
        navigate(fragmentSearchMoviesRouter.navigateToMovieDetailsFragment(movie = movie))

}