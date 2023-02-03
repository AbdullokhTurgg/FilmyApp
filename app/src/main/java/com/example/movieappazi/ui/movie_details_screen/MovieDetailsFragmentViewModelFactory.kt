package com.example.movieappazi.ui.movie_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.network.cloud.base.ResourceProvider
import com.example.data.network.retrofit.api.movie.MovieApi
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.CreditsResponseDomain
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MovieDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainRepositories.network.movie.MovieRepositories
import com.example.domain.domainRepositories.storage.MovieStorageRepository
import com.example.domain.iteractors.movie_iteractors.GetFavoriteMovieUseCase
import com.example.movieappazi.uiModels.movie.CreditsResponseUi
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MovieUi
import com.example.movieappazi.uiModels.movie.MoviesUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.ExperimentalCoroutinesApi

private const val MOVIE_ID_KEY = "movie_id_key"
private const val ACTORS_IDS_KEY = "actors_ids_key"

class MovieDetailsFragmentViewModelFactory @AssistedInject constructor(
    @Assisted(MOVIE_ID_KEY) private val movieId: Int,
    @Assisted(ACTORS_IDS_KEY) private val actorsIds: List<Int>,
    private val movieRepository: MovieRepositories,
    private val mapMovieDetails: BaseMapper<MovieDetailsDomain, MovieDetailsUi>,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: ResourceProvider,
    private val mapFromUiToDomain: BaseMapper<MovieUi, MovieDomain>,
    private val saveMovieRepository: MovieStorageRepository,
    private val mapCreditsResponseDomain: BaseMapper<CreditsResponseDomain, CreditsResponseUi>,
    private val mapMovieResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val getFavoriteMovieUseCase: GetFavoriteMovieUseCase,
) : ViewModelProvider.Factory {

    @OptIn(ExperimentalCoroutinesApi::class)
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MovieDetailsFragmentViewModel::class.java)
        return MovieDetailsFragmentViewModel(movieId = movieId,
            actorsIds = actorsIds,
            mapCreditsResponseDomain = mapCreditsResponseDomain,
            movieRepository = movieRepository,
            mapMovieDetails = mapMovieDetails,
            dispatchersProvider = dispatchersProvider,
            resourceProvider = resourceProvider,
//            mapPersons = mapPersons,
            saveMovieRepository = saveMovieRepository,
            mapFromUiToDomain = mapFromUiToDomain,
            mapMovieResponse = mapMovieResponse,
            getFavoriteMovieUseCase = getFavoriteMovieUseCase
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(MOVIE_ID_KEY) movieId: Int,
            @Assisted(ACTORS_IDS_KEY) actorsIds: List<Int>,
        ): MovieDetailsFragmentViewModelFactory
    }
}
