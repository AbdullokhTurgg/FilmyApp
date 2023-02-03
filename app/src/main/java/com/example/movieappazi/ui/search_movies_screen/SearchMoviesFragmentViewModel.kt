package com.example.movieappazi.ui.search_movies_screen

import androidx.lifecycle.viewModelScope
import com.example.data.network.cloud.base.ResourceProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.domainRepositories.network.movie.MovieRepositories
import com.example.domain.domainRepositories.storage.MovieStorageRepository
import com.example.domain.state.takeSuccess
import com.example.movieappazi.base.BaseViewModel
import com.example.movieappazi.extensions.createMutableSharedFlowAsLiveData
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.movie.MoviesUi
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
    private val resourceProvider: ResourceProvider,
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
            _error.emit(resourceProvider.handleException(it))
        }
    }

    fun saveMovie(movie: MovieUi) = viewModelScope.launch {
        storageRepository.saveMovieToDatabase(movie = saveMapper.map(movie))
    }

    fun launchMovieDetails(movie: MovieUi) =
        navigate(SearchMoviesFragmentDirections.actionNavSearchToMovieDetailsFragment(movie))

}