package com.example.movieappazi.ui.movie_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.data.network.cloud.base.ResourceProvider
import com.example.domain.assistant.DispatchersProvider
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.movie.MovieDetailsDomain
import com.example.domain.domainModels.movie.MoviesDomain
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainRepositories.network.movie.MovieRepositories
import com.example.domain.iteractors.movie_iteractors.get_movie_actors_details_usecase.GetMovieActorsDetailsUseCase
import com.example.movieappazi.uiModels.movie.MovieDetailsUi
import com.example.movieappazi.uiModels.movie.MoviesUi
import com.example.movieappazi.uiModels.person.PersonDetailsUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

private const val MOVIE_ID_KEY = "movie_id_key"
private const val ACTORS_IDS_KEY = "actors_ids_key"

class MovieDetailsFragmentViewModelFactory @AssistedInject constructor(
    @Assisted(MOVIE_ID_KEY) private val movieId: Int,
    @Assisted(ACTORS_IDS_KEY) private val actorsIds: List<Int>,
    private val movieRepository: MovieRepositories,
    private val mapMovieDetails: BaseMapper<MovieDetailsDomain, MovieDetailsUi>,
    private val mapMovieResponse: BaseMapper<MoviesDomain, MoviesUi>,
    private val mapPersons: BaseMapper<List<PersonDetailsDomain>, List<PersonDetailsUi>>,
    private val dispatchersProvider: DispatchersProvider,
    private val resourceProvider: ResourceProvider,
    private val getMovieActorsDetailsUseCase: GetMovieActorsDetailsUseCase,

    ) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == MovieDetailsFragmentViewModel::class.java)
        return MovieDetailsFragmentViewModel(movieId = movieId,
            actorsIds = actorsIds,
            movieRepository = movieRepository,
            mapMovieDetails = mapMovieDetails,
            mapMovieResponse = mapMovieResponse,
            dispatchersProvider = dispatchersProvider,
            resourceProvider = resourceProvider,
            mapPersons = mapPersons,
            getMovieActorsDetailsUseCase = getMovieActorsDetailsUseCase
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
