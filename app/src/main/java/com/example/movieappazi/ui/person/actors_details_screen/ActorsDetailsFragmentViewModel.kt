package com.example.movieappazi.ui.person.actors_details_screen

import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.repositories.network.person.PersonRepositories
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.person.PersonDetailsUi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.map


class ActorsDetailsFragmentViewModel(
    private val personId: Int,
    private val personRepositories: PersonRepositories,
    private val mapMovieDetails: BaseMapper<PersonDetailsDomain, PersonDetailsUi>,
) : BaseViewModel() {

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val personIdFlow = MutableStateFlow(personId)

    val personFlow =
        personIdFlow.map(personRepositories::getPersonDetails).map { it.map(mapMovieDetails) }


}