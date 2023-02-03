package com.example.movieappazi.ui.actors_details_screen

import androidx.lifecycle.ViewModel
import com.example.domain.base.BaseMapper
import com.example.domain.domainModels.person.PersonDetailsDomain
import com.example.domain.domainRepositories.network.person.PersonRepositories
import com.example.movieappazi.base.BaseViewModel
import com.example.movieappazi.uiModels.person.PersonDetailsUi
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