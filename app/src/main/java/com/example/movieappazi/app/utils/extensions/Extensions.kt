package com.example.movieappazi.app.utils.extensions

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import com.example.movieappazi.app.models.movie.ResponseState
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow


fun <T> createMutableSharedFlowAsSingleLiveEvent(): MutableSharedFlow<T> =
    MutableSharedFlow(0, 1, BufferOverflow.DROP_OLDEST)

fun <T> createMutableSharedFlowAsLiveData(): MutableSharedFlow<T> =
    MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)

fun changeResponseState(page: Int, totalPage: Int): ResponseState =
    ResponseState(totalPage = totalPage,
        page = page,
        isHasNextPage = page < totalPage,
        isHasPreviousPage = page > 1)

fun makeToast(message: String, context: Context) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

fun View.showView() {
    this.visibility = View.VISIBLE
}

fun View.hideView() {
    this.visibility = View.GONE
}

fun <T> LiveData<T>.observeNonNull(owner: LifecycleOwner, observer: (t: T) -> Unit) {
    this.observe(owner) {
        it?.let(observer)
    }
}