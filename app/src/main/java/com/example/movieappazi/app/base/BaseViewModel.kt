package com.example.movieappazi.app.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.example.domain.helper.DispatchersProvider
import com.example.movieappazi.app.utils.communications.NavigationCommunication
import com.example.movieappazi.app.utils.event.Event
import com.example.movieappazi.app.utils.extensions.createMutableSharedFlowAsSingleLiveEvent
import com.example.movieappazi.app.utils.navigation.NavCommand
import com.example.movieappazi.app.utils.navigation.NavigationCommand
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

abstract class BaseViewModel : ViewModel() {

    private var _navCommand = createMutableSharedFlowAsSingleLiveEvent<NavCommand>()
    val navCommand: SharedFlow<NavCommand> get() = _navCommand.asSharedFlow()

    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    private val navigationCommunication = NavigationCommunication.Base()

    protected fun addDisposable(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    private val dispatcher = DispatchersProvider.Base()

    private fun clearDisposables() {
        compositeDisposable.clear()
    }

    override fun onCleared() {
        clearDisposables()
    }

    private val _navigation = MutableLiveData<Event<NavigationCommand>>()
    val navigation: LiveData<Event<NavigationCommand>> get() = _navigation

    fun navigate(navCommand: NavCommand) = _navCommand.tryEmit(navCommand)

    fun navigateBack() =
        launchInBackground { navigationCommunication.put(Event(value = NavigationCommand.Back)) }

    fun <T> launchInBackground(backGroundCall: suspend () -> T) =
        dispatcher.launchInBackground(viewModelScope) { backGroundCall() }
}

