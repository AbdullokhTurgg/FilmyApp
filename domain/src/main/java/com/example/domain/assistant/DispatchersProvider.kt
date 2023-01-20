package com.example.domain.assistant

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

interface DispatchersProvider {

    fun default(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher

    class Base : DispatchersProvider {
        override fun default(): CoroutineDispatcher = Dispatchers.Default


        override fun io(): CoroutineDispatcher = Dispatchers.IO


        override fun main(): CoroutineDispatcher = Dispatchers.Main


        override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined


    }
}