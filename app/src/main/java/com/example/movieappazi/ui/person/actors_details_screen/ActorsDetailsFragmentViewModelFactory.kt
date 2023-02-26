package com.example.movieappazi.ui.person.actors_details_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.domain.base.BaseMapper
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.repositories.network.person.PersonRepositories
import com.example.movieappazi.app.models.person.PersonDetailsUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject

private const val PERSON_ID_KEY = "person_id_key"

class ActorsDetailsFragmentViewModelFactory @AssistedInject constructor(
    @Assisted(PERSON_ID_KEY) private val personId: Int,
    private val personRepositories: PersonRepositories,
    private val mapMovieDetails: BaseMapper<PersonDetailsDomain, PersonDetailsUi>,
    private val resourceProvider: HandleExeption,
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        require(modelClass == ActorsDetailsFragmentViewModel::class.java)
        return ActorsDetailsFragmentViewModel(personId = personId,
            personRepositories = personRepositories,
            mapMovieDetails = mapMovieDetails,
            resourceProvider = resourceProvider
        ) as T
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted(PERSON_ID_KEY) movieId: Int,
        ): ActorsDetailsFragmentViewModelFactory
    }
}