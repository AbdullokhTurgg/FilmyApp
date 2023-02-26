package com.example.movieappazi.ui.person.actors_details_screen

import androidx.lifecycle.viewModelScope
import com.example.domain.base.BaseMapper
import com.example.domain.models.person.PersonDetailsDomain
import com.example.domain.repositories.network.person.PersonRepositories
import com.example.movieappazi.app.base.BaseViewModel
import com.example.movieappazi.app.models.person.PersonDetailsUi
import com.example.movieappazi.app.utils.exception.HandleExeption
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*


class ActorsDetailsFragmentViewModel(
    private val personId: Int,
    private val personRepositories: PersonRepositories,
    private val mapMovieDetails: BaseMapper<PersonDetailsDomain, PersonDetailsUi>,
    private val resourceProvider: HandleExeption,
    ) : BaseViewModel() {

    private var _motionPosition = MutableStateFlow(0f)
    val motionPosition get() = _motionPosition.asStateFlow()
    fun updateMotionPosition(position: Float) = _motionPosition.tryEmit(position)

    private val _error = MutableSharedFlow<String>(replay = 0)
    val error get() = _error.asSharedFlow()

    private val personIdFlow = MutableStateFlow(personId)

    val personFlow = personIdFlow
        .flatMapLatest { personRepositories.getPersonDetails(it) }
        .map(mapMovieDetails::map)
        .flowOn(Dispatchers.Default)
        .catch { t: Throwable -> _error.emit(resourceProvider.hanEx(t)) }
        .shareIn(viewModelScope, SharingStarted.Lazily, 1)


}