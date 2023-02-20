package com.example.movieappazi.app.utils.communications

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import kotlinx.coroutines.flow.MutableSharedFlow

interface Communication<T> {

    fun observe(owner: LifecycleOwner, observer: Observer<T>)

    fun put(source: T)

    abstract class Base<T> : Communication<T> {

        private var mutableLiveData = MutableLiveData<T>()

        override fun observe(owner: LifecycleOwner, observer: Observer<T>) {
            mutableLiveData.observe(owner, observer)
        }

        override fun put(source: T) {
            mutableLiveData.postValue(source ?: source)
        }
    }
}