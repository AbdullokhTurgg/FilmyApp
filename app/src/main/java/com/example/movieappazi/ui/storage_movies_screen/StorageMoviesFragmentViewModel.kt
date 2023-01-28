package com.example.movieappazi.ui.storage_movies_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainRepositories.storage.MovieStorageRepository
import com.example.movieappazi.uiModels.movie.MovieUi
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class StorageMoviesFragmentViewModel @Inject constructor(
    private val savedRepository: MovieStorageRepository,
    private val mapFromListMovieDomainToUi: BaseMapper<List<MovieDomain>, List<MovieUi>>,
) : ViewModel() {

    val allMoviesFlow: Flow<List<MovieUi>> =
        savedRepository.getAllMoviesFromDatabase().map(mapFromListMovieDomainToUi::map)

    fun deleteMovies(movies: Int) = viewModelScope.launch {
        savedRepository.deleteMovieFromDatabase(movieId = movies)
    }
}