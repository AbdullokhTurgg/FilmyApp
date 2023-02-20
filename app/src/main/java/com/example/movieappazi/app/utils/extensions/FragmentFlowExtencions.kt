package com.example.movieappazi.app.utils.extensions

import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

inline fun Fragment.launchWhenViewStarted(
    crossinline block: suspend CoroutineScopeWrapper.() -> Unit,
) = viewLifecycleOwner.lifecycleScope.launchWhenStarted {
    CoroutineScopeWrapper(this).block()
}

class CoroutineScopeWrapper(
    val scope: CoroutineScope,
    var errorHandler: (Throwable) -> Unit = globalErrorHandler,
) {
    fun <T> Flow<T>.observe(action: suspend (T) -> Unit) =
        this.onEach(action).catch { errorHandler(it) }.launchIn(scope)


    companion object {
        var globalErrorHandler: (Throwable) -> Unit = { throw it }
    }
}