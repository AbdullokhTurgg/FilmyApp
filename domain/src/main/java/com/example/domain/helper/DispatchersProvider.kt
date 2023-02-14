package com.example.domain.helper

import kotlinx.coroutines.*

interface DispatchersProvider {

    fun default(): CoroutineDispatcher

    fun io(): CoroutineDispatcher

    fun main(): CoroutineDispatcher

    fun unconfined(): CoroutineDispatcher

    fun launchInBackground(scope: CoroutineScope, block: suspend CoroutineScope.() -> Unit): Job

    class Base : DispatchersProvider {
        override fun default(): CoroutineDispatcher = Dispatchers.Default


        override fun io(): CoroutineDispatcher = Dispatchers.IO


        override fun main(): CoroutineDispatcher = Dispatchers.Main


        override fun unconfined(): CoroutineDispatcher = Dispatchers.Unconfined

        override fun launchInBackground(
            scope: CoroutineScope,
            block: suspend CoroutineScope.() -> Unit,
        ): Job = scope.launch(kotlinx.coroutines.Dispatchers.IO, block = block)

    }
}